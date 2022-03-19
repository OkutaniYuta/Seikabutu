package com.example.demo.login.domain.service;

import com.example.demo.login.domain.model.ContractMonth;
import com.example.demo.login.domain.repository.jdbc.ContractMonthDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ContractMonthService {
    private final ContractMonthDao contractMonthDao;

    public List<ContractMonth> getMonthByContractId(int contractId) {
        return contractMonthDao.getMonthByContractId(contractId);
    }

    // コントラクトテーブルにinsert
    public void insertMonth(ContractMonth month) {
        //insert実行
        contractMonthDao.insertMonth(month);
    }
}
