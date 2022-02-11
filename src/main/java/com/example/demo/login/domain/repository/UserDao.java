package com.example.demo.login.domain.repository;

import org.springframework.dao.DataAccessException;

import com.example.demo.login.domain.model.User;

public interface UserDao {
	//DataAccessException
	
	//Userテーブルにデータを1件insert
	public void insertOne(User user) throws DataAccessException;
	
	public void emailUpdate(User user) throws DataAccessException;

	public User getOfficeName(String mailAddress) throws DataAccessException;

	public User selectUserId(String userId) throws DataAccessException;

	public User selectEmail(int userId) throws DataAccessException;

	
	
		
}