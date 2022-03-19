package com.example.demo.login.domain.repository.jdbc;

import com.example.demo.login.domain.model.ContractMonth;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Repository
public class ContractMonthDao {
    private final JdbcTemplate jdbc;

    public List<ContractMonth> getMonthByContractId(int contractId) throws DataAccessException {
        List<Map<String, Object>> getList = jdbc.queryForList("SELECT * FROM month"
                        + " WHERE contractId = ? "
                , contractId);

        List<ContractMonth> monthList = new ArrayList<>();
        for (Map<String, Object> map : getList) {
            ContractMonth contractMonth = new ContractMonth();
            contractMonth.setMonthId((int) map.get("monthId"));
            contractMonth.setContractId((int) map.get("contractId"));
            contractMonth.setYear((int) map.get("year"));
            contractMonth.setMonth((int) map.get("month"));
            monthList.add(contractMonth);
        }
        return monthList;
    }

    public void insertMonth(ContractMonth month) throws DataAccessException {
        //1件登録
        jdbc.update("INSERT INTO month("
                        + " monthId,"
                        + " contractId,"
                        + " year,"
                        + " month)"
                        + " VALUES(?, ?, ?, ?)"
                , month.getMonthId()
                , month.getContractId()
                , month.getYear()
                , month.getMonth());
    }

    private ContractMonth convert(Map<String, Object> map) {
        ContractMonth month = new ContractMonth();
        month.setMonthId((int) map.get("monthId"));
        month.setContractId((int) map.get("contractId"));
        month.setYear((int) map.get("year"));
        month.setMonth((int) map.get("month"));
        return month;
    }
}
