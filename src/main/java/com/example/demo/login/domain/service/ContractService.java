package com.example.demo.login.domain.service;

import com.example.demo.login.domain.model.Contract;
import com.example.demo.login.domain.repository.jdbc.ContractDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
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

    public void updateContract(int contractId, String contractTime, LocalTime startTime, LocalTime breakTime, LocalTime endTime) {
        Contract contract = new Contract();
        contract.setContractId(contractId);
        contract.setContractTime(contractTime);
        contract.setStartTime(startTime);
        contract.setBreakTime(breakTime);
        contract.setEndTime(endTime);
        contractDao.updateContract(contract);
    }

    public void updateEndDate(int userId, LocalDate endDate) {
        int contractId = contractDao.getContractIdByUserId(userId);
        contractDao.updateEndDateByContractId(contractId, endDate);
    }

    //オフィスネームゲットメソッド
    public String getOfficeNameByUserId(int userId) {
        return contractDao.getOfficeNameByUserId(userId);
    }

    public String getOfficeNameByContractId(int contractId) {
        return contractDao.getOfficeNameByContractId(contractId);
    }
}
