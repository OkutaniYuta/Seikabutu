package com.example.demo.login.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.login.domain.model.Contract;
import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.repository.jdbc.ContractDao;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ContractService {
	private final ContractDao dao;

	//コントラクトテーブルとユーザーテーブルの情報を取得する
	public List<User> getContractByEmail(String email) {
		return dao.getContractByEmail(email);
	}
	//コントラクトテーブルの情報のみを取得
	public List<User> getOnlyContractByUserId(int userId) {
		return dao.getOnlyContractByUserId(userId);
	}
	//コントラクトテーブルにinsert
	public void insertContract(Contract contract) {
		//insert実行
		dao.insertContract(contract);
	}
}
