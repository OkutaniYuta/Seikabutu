package com.example.demo.login.domain.repository.jdbc;

import com.example.demo.login.domain.model.Month;
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

    public List<Month> getMonthListByContractId(int contractId) throws DataAccessException {
        List<Map<String, Object>> getList = jdbc.queryForList("SELECT * FROM month"
                        + " WHERE contractId = ? "
                , contractId);

        List<Month> monthList = new ArrayList<>();
        for (Map<String, Object> map : getList) {
            Month month = convert(map);
            monthList.add(month);
        }
        return monthList;
    }

    public Month getMonthIdByContractId(int contractId) throws DataAccessException {
        Map<String, Object> map = jdbc.queryForMap("SELECT contractId FROM contract "
                        + " WHERE contractId = ? ORDER BY monthId desc limit 1"
                , contractId);
        Month month = new Month(); // 結果返却用の変数
        month.setContractId((int) map.get("contractId")); // 取得したデータを結果返却用の変数にセット
        return month;
    }

    public void insertMonth(Month month) throws DataAccessException {
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

    private Month convert(Map<String, Object> map) {
        Month month = new Month();
        month.setMonthId((int) map.get("monthId"));
        month.setContractId((int) map.get("contractId"));
        month.setYear((int) map.get("year"));
        month.setMonth((int) map.get("month"));
        return month;
    }
}
