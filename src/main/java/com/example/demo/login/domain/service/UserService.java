package com.example.demo.login.domain.service;

import java.util.List;

import com.example.demo.login.domain.model.Contract;
import com.example.demo.login.domain.repository.jdbc.ContractDao;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.repository.jdbc.UserDao;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	private final UserDao dao;
	private final ContractDao contractDao;
	private final AuthenticationService authenticationService;

	//insert用メソッド
	public void insert(User user) {
		//insert実行
		dao.insert(user);
	}
	//オフィスネームゲットメソッド
	public User getOfficeNameByEmail(String email) {
		return dao.getOfficeNameByEmail(email);
	}
	
	public User getEmailByEmail(String email) {
		return dao.getEmailByEmail(email);
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
	public List<User> getContractByEmail(String email) {
		return dao.getContractByEmail(email);
	}
	//コントラクトテーブルの情報のみを取得
	public List<Contract> getOnlyContractByUserId(int userId) {
		return contractDao.getOnlyContractByUserId(userId);
	}
	//コントラクトテーブルにinsert
	public void insertIntoContract(User user) {
		//insert実行
		dao.insert(user);
	}
}
