package com.example.demo.login.domain.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.example.demo.login.domain.model.User;

public interface UserDao {
	//DataAccessException
	//Userテーブルの件数を取得
	public int count() throws DataAccessException;
	
	//Userテーブルにデータを1件insert
	public void insertOne(User user) throws DataAccessException;
	
	//Userテーブルを1件取得
	public User selectOne(String userName) throws DataAccessException;
	
	//Userテーブルの全データを取得
	public List<User> selectMany() throws DataAccessException;
	
	//Userテーブルを1件更新
	public int updateOne(User user) throws DataAccessException;
	
	//Userテーブルを1件削除
	public int deleteOne(String userName) throws DataAccessException;
}