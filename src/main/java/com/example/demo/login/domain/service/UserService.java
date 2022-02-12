package com.example.demo.login.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.repository.UserDao;

@Service
public class UserService {
	@Autowired
	 UserDao dao;
		
	//insert用メソッド
	public void insert(User user) {
		//insert実行
		dao.insertOne(user);	
	}
	
	//オフィスネームゲットメソッド
	public User getOfficeName(String mailAddress) {
		
		return dao.getOfficeName(mailAddress);
		
	}
	
	//メールアドレスUpdate用メソッド
	public void emailUpdate(User user) {
		
		dao.emailUpdate(user);
	}

	//userId取得メソッド
	public User selectUserId(String mailAddress) {
		
		return dao.selectUserId(mailAddress);
	}
	
	//ログインユーザーのメールアドレス
	public User selectEmail(int userId) {
		
		return dao.selectEmail(userId);
	}
	
	
}