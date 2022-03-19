package com.example.demo.login.domain.service;

import com.example.demo.login.domain.model.ContractMonth;
import com.example.demo.login.domain.repository.jdbc.ContractMonthDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class ContractMonthService {
    private final ContractMonthDao contractMonthDao;

    public List<ContractMonth> getMonthByContractId(int contractId) {
        return contractMonthDao.getMonthByContractId(contractId);
    }

    public List<Map<String, String>> getYearMonth(List<ContractMonth> contractMonthList) {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();

        int listSize = contractMonthList.size();

        for (int i = 0; i < listSize; i++) {
            Map<String, String> map = new HashMap<>();
            ContractMonth month = contractMonthList.get(i);
            String getYear = Integer.toString(month.getYear());
            String getMonth = String.format("%02d", month.getMonth());
            String yearMonth = getYear + "年" + getMonth + "月";
            map.put("YearMonth", yearMonth);
            list.add(map);
        }
        return list;

    }

    // コントラクトテーブルにinsert
    public void insertMonth(ContractMonth month) {
        //insert実行
        contractMonthDao.insertMonth(month);
    }
}
