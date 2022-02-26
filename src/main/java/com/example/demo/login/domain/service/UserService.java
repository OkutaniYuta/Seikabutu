package com.example.demo.login.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.repository.UserDao;

@Service
public class UserService {
	@Autowired
	UserDao dao;

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	AuthenticationService authenticationService;

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
	public void updateEmail(String newEmail, String originalEmail, UserDetails auth) {
		dao.updateEmail(newEmail, originalEmail);
		authenticationService.changeEmail(newEmail, auth);
	}

	//パスワード更新用メソッド
	public void updatePassword(String newEncodedPassword, UserDetails auth) {
		String originalEmail = auth.getUsername();
		dao.updatePassword(newEncodedPassword, originalEmail);
		authenticationService.changePassword(newEncodedPassword, auth);		
	}
	
	//コントラクトテーブルの情報を取得する
	public List<User> getByContract(String mailAddress) {
		return dao.getByContrac(mailAddress);
	}
}