package com.example.demo.login.domain.service;

import com.example.demo.login.domain.model.Month;
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

    public List<Month> getMonthListByContractId(int contractId) {
        return contractMonthDao.getMonthListByContractId(contractId);
    }

    public List<Map<String, String>> getYearMonth(List<Month> monthList) {
        List<Map<String, String>> list = new ArrayList<>();

        for (Month month : monthList) {
            Map<String, String> map = new HashMap<>();
            String monthId = String.valueOf(month.getMonthId());
            String contractId = String.valueOf(month.getContractId());
            String yearMonth = String.format("%d年%02d月", month.getYear(), month.getMonth());
            map.put("YearMonth", yearMonth);
            map.put("MonthId", monthId);
            map.put("ContractId", contractId);
            list.add(map);
        }
        return list;
    }

    // コントラクトテーブルにinsert
    public void insertMonth(Month month) {
        //insert実行
        contractMonthDao.insertMonth(month);
    }
}
