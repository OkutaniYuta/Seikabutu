package com.example.demo.login.domain.repository.jdbc;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.repository.UserDao;
import com.example.demo.login.domain.service.UserService;

@Repository
public class UserDaoJdbcImpl implements UserDao {
	
	@Autowired
	JdbcTemplate jdbc;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	UserService userService;
	
	//Userテーブルに1件insert
	@Override
	public void insert(User user) throws DataAccessException {
		
		//パスワード暗号化
		String password = passwordEncoder.encode(user.getPassword());
		
		//1件登録
		jdbc.update("INSERT INTO user("
				+ " userName,"
				+ " email,"
				+ " password,"
				+ " role,"
				+ " userStatus,"
				+ " requestedAt)"
				+ " VALUES(?, ?, ?, ?, ?, ?)"
				,user.getUserName()
				,user.getEmail()
				,password
				,user.getRole()
				,user.getUserStatus()
				,user.getReqestedAt());
		
		
	}
	
	//メールアドレス更新用メソッド
	public void updateEmail(String newEmail, String originalEmail)  throws DataAccessException {
		
		jdbc.update("UPDATE user"
				+ " SET"
				+ " email = ?"
				+ " WHERE email = ?"
				, newEmail
				, originalEmail);
	}
	
	//パスワード更新用メソッド
	public void updatePassword(String newPassword, String mailAddress)  throws DataAccessException {
		
		User user = new User();
		
		//パスワード暗号化
		String newPassword1 = passwordEncoder.encode(newPassword);
		
		jdbc.update("UPDATE user"
				+ " SET"
				+ " password = ?"
				+ " WHERE email = ?"
				, newPassword1
				, mailAddress);
	}

	@Override
	public User getByOfficeName(String mailAddress) throws DataAccessException {
		Map<String, Object> map = jdbc.queryForMap("SELECT * FROM user INNER JOIN contract on user.userId = contract.userId"
				+ " WHERE email = ?"
				, mailAddress);
		
		//結果返却用の変数
		User user = new User();
		
		//取得したデータを結果返却用の変数にセットしていく
		user.setOfficeName((String)map.get("officeName"));
		
		
		
		return user;
		
	}
	
	//ユーザーIDとパスワードを取得
	
	
	
	
}