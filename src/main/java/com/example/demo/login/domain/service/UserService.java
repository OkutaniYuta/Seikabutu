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
		dao.insert(user);	
	}
	
	//オフィスネームゲットメソッド
	public User getByOfficeName(String mailAddress) {
		
		return dao.getByOfficeName(mailAddress);
		
	}
	
	//メールアドレスUpdate用メソッド
	public void updateEmail(String newEmail, String originalEmail) {
		
		dao.updateEmail(newEmail, originalEmail);
	}
	
	//パスワード更新用メソッド
	public void updatePassword(String newPassword, int userId) {
		dao.updatePassword(newPassword, userId); 
	}
	
	
}