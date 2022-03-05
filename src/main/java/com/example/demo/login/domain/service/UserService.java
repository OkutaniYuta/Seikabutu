package com.example.demo.login.domain.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.repository.jdbc.UserDao;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	private final UserDao dao;
	private final AuthenticationService authenticationService;

	//insert用メソッド
	public void insert(User user) {
		//insert実行
		dao.insert(user);	
	}
	//オフィスネームゲットメソッド
	public User getByOfficeName(String mailAddress) {
		return dao.getByOfficeName(mailAddress);
	}
	//ログインユーザーのuserStatu取得
		public User getByUserStatus(String mailAddress) {
			return dao.getByUserStatus(mailAddress);
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
	//コントラクトテーブルとユーザーテーブルの情報を取得する
	public List<User> getByContract(String mailAddress) {
		return dao.getByContract(mailAddress);
	}
	//コントラクトテーブルの情報のみを取得
	public List<User> getByOnlyContract(int userId) {
		return dao.getByOnlyContract(userId);
	}
}
