package com.example.demo.login.domain.repository.jdbc;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.repository.UserDao;

@Repository
public class UserDaoJdbcImpl implements UserDao {
	@Autowired
	JdbcTemplate jdbc;
	
	@Override
	public int count() throws DataAccessException {
		return 0;
	}
	
	//Userテーブルに1件insert
	@Override
	public int insertOne(User user) throws DataAccessException {
		return 0;
	}
	
	//Userテーブルのデータを1件取得
	@Override
	public User selectOne(String userName) throws DataAccessException {
		return null;
	}
	
	//Userテーブルの全データを取得
	@Override
	public List<User> selectMany() throws DataAccessException {
		return null;
	}
	
	//Userテーブルを1件更新
	@Override
	public int updateOne(User user) throws DataAccessException {
		return 0;
	}
	
	//Userテーブルを1件削除
	@Override
	public int deleteOne(String userName) throws DataAccessException {
		return 0;
	}
	
	
}