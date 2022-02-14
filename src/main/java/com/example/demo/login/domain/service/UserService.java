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
	public void insertByUserDeteal(User user) {
		//insert実行
		dao.insertByUserDeteal(user);	
	}
	
	//オフィスネームゲットメソッド
	public User getByOfficeName(String mailAddress) {
		
		return dao.getByOfficeName(mailAddress);
		
	}
	
	//メールアドレスUpdate用メソッド
	public void updateByEmail(User user) {
		
		dao.updateByEmail(user);
	}

	//userId取得メソッド
	public User selectByUserId(String userId) {
		
		return dao.selectByUserId(userId);
	}
	
	//ログインユーザーのメールアドレス
	public User selectEmail(int userId) {
		
		return dao.selectByEmail(userId);
	}
	
	
}