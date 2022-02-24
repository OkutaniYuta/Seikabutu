package com.example.demo.login.domain.service;

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
	public void updateEmail(String newEmail, String originalEmail, String confirmEmail, UserDetails auth) {
		if(newEmail.equals(confirmEmail)) {
			dao.updateEmail(newEmail, originalEmail);
			authenticationService.change(newEmail, auth.getPassword(), auth.getAuthorities());
		}
	}

	//パスワード更新用メソッド
	public void updatePassword(String nowPassword, String originalEncodedPassword, String newPassword, 
				String confirmPassword, String mailAddress, String newEncodedPassword,UserDetails auth) {
		// 以下を満たす場合,DBを更新,セッションを更新
				//    * 現在ログインしているユーザーのパスワードと画面の「現在のパスワード入力」が等しい
				//    * 「新しいパスワード入力」と「新しいパスワードの確認」が等しい
		if (passwordEncoder.matches(nowPassword, originalEncodedPassword) && newPassword.equals(confirmPassword)) {
			dao.updatePassword(newEncodedPassword, mailAddress); 
			authenticationService.change(auth.getUsername(), newEncodedPassword, auth.getAuthorities());
		}		
	}
	
	//コントラクトテーブルの情報を取得する
	public User getByContract(String mailAddress) {
		return dao.getByContrac(mailAddress);
	}
}