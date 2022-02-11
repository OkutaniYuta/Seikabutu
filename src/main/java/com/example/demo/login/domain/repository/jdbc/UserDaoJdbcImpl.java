package com.example.demo.login.domain.repository.jdbc;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.example.demo.login.controller.EmailChangeController;
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
	EmailChangeController email;
	
	@Autowired
	UserService userService;
	
	//Userテーブルに1件insert
	@Override
	public void insertOne(User user) throws DataAccessException {
		
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
	@Override
	public void emailUpdate(User user)  throws DataAccessException {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //Principalからログインユーザの情報を取得
        String mailAddress = auth.getName();
		
		
		jdbc.update("UPDATE user"
				+ " SET"
				+ " email = ?"
				+ " WHERE email = ?"
				, user.getEmail()
				, mailAddress);
	}
	
	//ログインユーザーのユーザーIDを取得
	@Override
	public User selectUserId(String userId) throws DataAccessException {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //Principalからログインユーザの情報を取得
        String mailAddress = auth.getName();
		
		Map<String, Object> map = jdbc.queryForMap("SELECT USERID FROM user"
				+ " WHERE email = ?" , mailAddress);
		
		User user = new User();
		
		user.setUserId((int)map.get("userId"));
		
		return user;
	}
	
	//ユーザーIDをキーにログインユーザーのメールアドレスを1件取得
	@Override
	public  User selectEmail(int email) throws DataAccessException {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //Principalからログインユーザの情報を取得
        String mailAddress = auth.getName();
		
		int userId = userService.selectUserId(mailAddress).getUserId();
		
		Map<String, Object> map = jdbc.queryForMap("SELECT EMAIL FROM user"
				+ " WHERE USERID = ?" , userId);
		
		User user = new User();
		
		user.setUserId((int)map.get("userId"));
		
		return user;
	}

	@Override
	public User getOfficeName(String mailAddress) throws DataAccessException {
		Map<String, Object> map = jdbc.queryForMap("SELECT * FROM user INNER JOIN contract on user.userId = contract.userId"
				+ " WHERE email = ?"
				, mailAddress);
		
		//結果返却用の変数
		User user = new User();
		
		//取得したデータを結果返却用の変数にセットしていく
		user.setOfficeName((String)map.get("officeName"));
		
		
		return user;
		
	}
	
	
	
}