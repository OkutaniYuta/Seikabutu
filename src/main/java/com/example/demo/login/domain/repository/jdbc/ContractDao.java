package com.example.demo.login.domain.repository.jdbc;

import com.example.demo.login.domain.model.Contract;
import com.example.demo.login.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Repository
public class ContractDao {
    private final JdbcTemplate jdbc;

    //ログインユーザーの会社名を1件取得
    public User getOfficeNameByEmail(String email) throws DataAccessException {
        Map<String, Object> map = jdbc.queryForMap("SELECT * FROM user INNER JOIN contract on user.userId = contract.userId"
                        + " WHERE email = ? ORDER BY startDate desc limit 1"
                , email); // 勤務開始日(startDate)を降順(desc)で並び替え、一番上のものをとる。つまり最新(現在契約中)の会社を指定できる
        User user = new User(); // 結果返却用の変数
        user.setOfficeName((String) map.get("officeName")); // 取得したデータを結果返却用の変数にセット
        return user; // return convert　convertメソッドにcontractテーブルの
    }

    public List<User> getContractByEmail(String mailAddress) throws DataAccessException {
        List<Map<String, Object>> getList = jdbc.queryForList("SELECT * FROM user INNER JOIN contract on user.userId = contract.userId"
                        + " WHERE email = ? ORDER BY startDate desc"
                , mailAddress);
        List<User> userList = new ArrayList<>();
        for (Map<String, Object> map : getList) {
            User user = new User();
            // Userインスタンスに取得したデータをセットする
            user.setContractId((int) map.get("contractId"));
            user.setUserId((int) map.get("userId"));
            user.setStartTime((Time) map.get("startTime"));
            user.setBreakTime((Time) map.get("breakTime"));
            user.setEndTime((Time) map.get("endTime"));
            user.setStartDate((Date) map.get("startDate"));
            user.setEndDate((Date) map.get("endDate"));
            user.setOfficeName((String) map.get("officeName"));
            userList.add(user);
        }
        return userList;
    }

    public List<Contract> getOnlyContractByUserId(int userId) throws DataAccessException {
        List<Map<String, Object>> getList = jdbc.queryForList("SELECT * FROM contract"
                        + " WHERE userId = ? "
                , userId);
//		return getList.stream().map(this::convert).collect(toList());

        List<Contract> contractList = new ArrayList<>();
        for (Map<String, Object> map : getList) {
            Contract contract = convert(map);
            contractList.add(contract);
        }
        return contractList;
    }

    public void insertContract(Contract contract) throws DataAccessException {
        //1件登録
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

    private Contract convert(Map<String, Object> map) {
        Contract contract = new Contract();
        contract.setContractId((int) map.get("contractId"));
        contract.setUserId((int) map.get("userId"));
        contract.setContractTime((String) map.get("contractTime"));
        contract.setStartTime((LocalTime) map.get("startTime"));
        contract.setBreakTime((LocalTime) map.get("breakTime"));
        contract.setEndTime((LocalTime) map.get("endTime"));
        contract.setStartDate((LocalDate) map.get("startDate"));
        contract.setEndDate((LocalDate) map.get("endDate"));
        contract.setOfficeName((String) map.get("officeName"));
        return contract;
    }
}
