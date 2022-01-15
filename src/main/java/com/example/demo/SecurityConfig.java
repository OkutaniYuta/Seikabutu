package com.example.demo;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	//パスワードエンコーダーのBean定義
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	//データソース
	@Autowired
	private DataSource dataSource;
	
	//メールアドレスとパスワードを取得するSQL文
	private static final String USER_SQL = "SELECT"
			+ " email,"
			+ " password,"
			+ " true"
			+ " user"
			+ " WHERE"
			+ " email = ?";
	
	//ユーザーのロールを取得するSQL文
	private static final String ROLE_SQL = "SELECT"
			+ " email,"
			+ " role"
			+ " FORM" 
			+ " user"
			+ " WHERE"
			+ " email = ?";
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		//静的リソースへのアクセスには、セキュリティーを適用しない
		web.ignoring().antMatchers("/webjars/**", "/css/**");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//ログイン不要ページの設定
		http
			.authorizeRequests()
				.antMatchers("/webjars/**").permitAll()
				.antMatchers("/css/**").permitAll()
				.antMatchers("/login").permitAll()
				.antMatchers("/signup").permitAll()
				.antMatchers("/jdbc:h2:mem:testdb;MODE=MYSQL").permitAll() //1/11 Securityを入れたらDBにアクセスできなくなった未解決
				.anyRequest().authenticated();
		
		//ログイン処理
		http
			.formLogin()
				.loginProcessingUrl("/login")
				.loginPage("/login")
				.failureUrl("/login")
				.usernameParameter("email")
				.passwordParameter("password")
				.defaultSuccessUrl("/home", true);
		
		
		//CSRF対策を無効に設定
		http.csrf().disable();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		//ログイン処理時にユーザー情報をDBから取得する
		auth.jdbcAuthentication()
			.dataSource(dataSource)
			.usersByUsernameQuery(USER_SQL)
			.authoritiesByUsernameQuery(ROLE_SQL)
			.passwordEncoder(passwordEncoder());
	}
}