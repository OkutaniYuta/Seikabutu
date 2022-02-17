package com.example.demo.login.domain.repository;

import org.springframework.dao.DataAccessException;

import com.example.demo.login.domain.model.User;

public interface UserDao {
	//DataAccessException
	
	//Userテーブルにデータを1件insert
	public void insert(User user) throws DataAccessException;
	
	public void updateEmail(String newEmail, String originalEmail) throws DataAccessException;

	public User getByOfficeName(String mailAddress) throws DataAccessException;
	
	public void updatePassword(String newPassword, String originalPassword) throws DataAccessException;
		
}