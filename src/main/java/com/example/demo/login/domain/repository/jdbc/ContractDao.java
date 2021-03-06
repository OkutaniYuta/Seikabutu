package com.example.demo.login.domain.repository.jdbc;

import com.example.demo.login.domain.model.Contract;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Repository
public class ContractDao {
    private final JdbcTemplate jdbc;

    public String getOfficeNameByUserId(int userId) throws DataAccessException {
        Map<String, Object> map = jdbc.queryForMap("SELECT officeName FROM contract "
                        + " WHERE userId = ? ORDER BY startDate desc limit 1"
                , userId);
        String officeName = (String) map.get("officeName");
        return officeName;
    }

    public String getOfficeNameByContractId(int contractId) throws DataAccessException {
        Map<String, Object> map = jdbc.queryForMap("SELECT officeName FROM contract "
                        + " WHERE contractId = ?"
                , contractId);
        String officeName = (String) map.get("officeName");
        return officeName;
    }

    public List<Contract> getContractByEmail(String email) throws DataAccessException {
        List<Map<String, Object>> getList = jdbc.queryForList("SELECT * FROM user INNER JOIN contract on user.userId = contract.userId"
                        + " WHERE email = ? ORDER BY startDate desc"
                , email);
        List<Contract> contractList = new ArrayList<>();
        for (Map<String, Object> map : getList) {
            Contract contract = convert(map);
            contractList.add(contract);
        }
        return contractList;
    }

    public List<Contract> getOnlyContractByUserId(int userId) throws DataAccessException {
        List<Map<String, Object>> getList = jdbc.queryForList("SELECT * FROM contract"
                        + " WHERE userId = ? "
                , userId);
//		return getList.stream().map(this::convert).collect(toList()); 78????????????83???????????????????????????

        List<Contract> contractList = new ArrayList<>();
        for (Map<String, Object> map : getList) {
            Contract contract = convert(map);
            contractList.add(contract);
        }
        return contractList;
    }

    public Integer getContractIdByUserId(int userId) throws DataAccessException {
        return jdbc.queryForObject("SELECT contractId FROM contract "
                + " WHERE userId = ? ORDER BY startDate desc limit 1", Integer.class, userId);
    }

    public void insertContract(Contract contract) throws DataAccessException {
        //1?????????
        jdbc.update("INSERT INTO contract("
                        + " userId,"
                        + " contractTime,"
                        + " startTime,"
                        + " breakTime,"
                        + " endTime,"
                        + " startDate,"
                        + " officeName)"
                        + " VALUES(?, ?, ?, ?, ?, ?, ?)"
                , contract.getUserId()
                , contract.getContractTime()
                , contract.getStartTime()
                , contract.getBreakTime()
                , contract.getEndTime()
                , contract.getStartDate()
                , contract.getOfficeName());
    }

    public void updateContract(Contract contract) throws DataAccessException {
        //1?????????
        jdbc.update("UPDATE contract"
                        + " SET"
                        + " contractTime = ?,"
                        + " startTime = ?,"
                        + " breakTime = ?,"
                        + " endTime = ?"
                        + " WHERE contractId = ?"
                , contract.getContractTime()
                , contract.getStartTime()
                , contract.getBreakTime()
                , contract.getEndTime()
                , contract.getContractId());
    }

    public void updateEndDateByContractId(int contractId, LocalDate endDate) throws DataAccessException {
        jdbc.update("UPDATE contract"
                        + " SET"
                        + " endDate = ?"
                        + " WHERE contractId = ?"
                , endDate
                , contractId);
    }

    private Contract convert(Map<String, Object> map) {
        Contract contract = new Contract();
        contract.setContractId((int) map.get("contractId"));
        contract.setUserId((int) map.get("userId"));
        contract.setContractTime((String) map.get("contractTime"));
        contract.setStartTime(((java.sql.Time) map.get("startTime")).toLocalTime());
        contract.setBreakTime(((java.sql.Time) map.get("breakTime")).toLocalTime());
        contract.setEndTime(((java.sql.Time) map.get("endTime")).toLocalTime());
        contract.setStartDate(((java.sql.Date) map.get("startDate")).toLocalDate());
        contract.setEndDate(((java.sql.Date) map.get("endDate")).toLocalDate());
        contract.setOfficeName((String) map.get("officeName"));
        return contract;
    }
}
