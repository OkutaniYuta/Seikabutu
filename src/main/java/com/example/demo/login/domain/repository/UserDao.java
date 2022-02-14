package com.example.demo.login.domain.repository;

import org.springframework.dao.DataAccessException;

import com.example.demo.login.domain.model.User;

public interface UserDao {
	//DataAccessException
	
	//Userテーブルにデータを1件insert
	public void insertByUserDeteal(User user) throws DataAccessException;
	
	public void updateByEmail(User user) throws DataAccessException;

	public User getByOfficeName(String mailAddress) throws DataAccessException;

	public User selectByUserId(String userId) throws DataAccessException;

	public User selectByEmail(int userId) throws DataAccessException;

	
	
		
}