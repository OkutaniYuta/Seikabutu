package com.example.demo.login.domain.repository.jdbc;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.repository.UserDao;

@Repository
public class UserDaoJdbcImpl implements UserDao {
	
	@Autowired
	JdbcTemplate jdbc;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	//Userテーブルの件数を取得
	@Override
	public int count() throws DataAccessException {
		
		//全件取得してカウント
		int count = jdbc.queryForObject("SELECT COUNT(*) FORM user", Integer.class);
		
		return count;
	}
	
	//Userテーブルに1件insert
	@Override
	public int insertOne(User user) throws DataAccessException {
		
		//パスワード暗号化
		String password = passwordEncoder.encode(user.getPassword());
		
		//1件登録
		int rowNumber = jdbc.update("INSERT INTO user("
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
		
		return rowNumber;
	}
	
	
	//Userテーブルのデータを1件取得
	@Override
	public User selectOne(String userName) throws DataAccessException {
		return null;
	}
	
	//Userテーブルの全データを取得
	@Override
	public List<User> selectMany() throws DataAccessException {
		
		//M_USERテーブルのデータを全件取得
		List<Map<String, Object>> getList = jdbc.queryForList("SELECT * FORM m_user");
		
		//結果返却用の変数
		List<User> userList = new ArrayList<>();
		
		//取得したデータを結果返却用のListに格納していく
		for(Map<String, Object> map:getList) {
			
			//Userインスタンスの生成
			User user = new User();
			
			//Userインスタンスに取得したデータをセットする
			user.setUserId((Integer)map.get("userId"));
			user.setUserName((String)map.get("userName"));
			user.setEmail((String)map.get("email"));
			user.setPassword((String)map.get("password"));
			user.setRole((String)map.get("role"));
			user.setUserStatus((Integer)map.get("status"));
			user.setReqestedAt((Integer)map.get("reqestedAt"));
			
			//結果返却用のListに追加
			userList.add(user);
		}
		
		return userList;
	}
	
	//Userテーブルを1件更新
	@Override
	public int updateOne(User user) throws DataAccessException {
		 //パスワード暗号化
        String password = passwordEncoder.encode(user.getPassword());

        //１件更新
        int rowNumber = jdbc.update("UPDATE USER"
                + " SET"
                + " password = ?,"
                + " userName = ?,"
                + " email = ?,"
                + " userStatus = ?"
                + " requestedAt = ?"
                + " WHERE userId = ?"
                ,user.getUserId()
				,user.getUserName()
				,user.getEmail()
				,password
				,user.getRole()
				,user.getUserStatus()
				,user.getReqestedAt());

		return rowNumber;
	}
	
	//Userテーブルを1件削除
	@Override
	public int deleteOne(String userName) throws DataAccessException {
		return 0;
	}
	
	
}