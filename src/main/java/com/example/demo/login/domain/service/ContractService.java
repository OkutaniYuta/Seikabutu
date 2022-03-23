package com.example.demo.login.domain.service;

import com.example.demo.login.domain.model.Contract;
import com.example.demo.login.domain.repository.jdbc.ContractDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ContractService {
    private final ContractDao contractDao;

    //コントラクトテーブルとユーザーテーブルの情報を取得する
    public List<Contract> getContractByEmail(String email) {
        return contractDao.getContractByEmail(email);
    }

    //コントラクトテーブルの情報のみを取得
    public List<Contract> getOnlyContractByUserId(int userId) {
        return contractDao.getOnlyContractByUserId(userId);
    }

    //コントラクトテーブルにinsert
    public void insertContract(Contract contract) {
        //insert実行
        contractDao.insertContract(contract);
    }

    //オフィスネームゲットメソッド
    public Contract getOfficeNameByUserId(int userId) {
        return contractDao.getOfficeNameByUserId(userId);
    }

    public Contract getOfficeNameByContractId(int contractId) {
        return contractDao.getOfficeNameByContractId(contractId);
    }

    public Contract getContractIdByUserId(int userId) {
        return contractDao.getContractIdByUserId(userId);
    }
}
