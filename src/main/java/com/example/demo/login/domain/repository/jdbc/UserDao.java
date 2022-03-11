package com.example.demo.login.domain.repository.jdbc;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.example.demo.login.domain.model.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class UserDao {
	private final JdbcTemplate jdbc;
	private final PasswordEncoder passwordEncoder;

	// Userテーブルに1件insert
	public void insert(User user) throws DataAccessException {
		//パスワード暗号化
		String password = passwordEncoder.encode(user.getPassword()); // ここのパスワードの暗号化はサービスで行う?
		//1件登録
		jdbc.update("INSERT INTO user("
				+ " userName,"
				+ " email,"
				+ " password,"
				+ " role,"
				+ " userStatus,"
				+ " requestedAt)"
				+ " VALUES(?, ?, ?, ?, ?, ?)"
				,user.getUserName()
				,user.getEmail()
				,password
				,user.getRole()
				,user.getUserStatus()
				,user.getRequestedAt());
	}

	// メールアドレス更新用メソッド
	public void updateEmail(String newEmail, String originalEmail)  throws DataAccessException {
		jdbc.update("UPDATE user"
				+ " SET"
				+ " email = ?"
				+ " WHERE email = ?"
				, newEmail
				, originalEmail);
	}

	// パスワード更新用メソッド
	public void updatePassword(String newPassword, String mailAddress)  throws DataAccessException {
		jdbc.update("UPDATE user"
				+ " SET"
				+ " password = ?"
				+ " WHERE email = ?"
				, newPassword
				, mailAddress);
	}

	//ログインユーザーの会社名を1件取得
	public User getOfficeNameByEmail(String mailAddress) throws DataAccessException {
		Map<String, Object> map = jdbc.queryForMap("SELECT * FROM user INNER JOIN contract on user.userId = contract.userId"
				+ " WHERE email = ? ORDER BY startDate desc limit 1" 
				, mailAddress); // 勤務開始日(startDate)を降順(desc)で並び替え、一番上のものをとる。つまり最新(現在契約中)の会社を指定できる
		User user = new User(); // 結果返却用の変数
		user.setOfficeName((String)map.get("officeName")); // 取得したデータを結果返却用の変数にセット
		return user; // return convert　convertメソッドにcontractテーブルの
	}
	
	public User getEmailByEmail(String email) throws DataAccessException {
		Map<String, Object> map = jdbc.queryForMap("SELECT * FROM user "
				+ " WHERE email = ?" 
				, email); 
		return convert(map);
	}
	
	public List<User> getContractByEmail(String mailAddress) throws DataAccessException {
		List<Map<String, Object>> getList = jdbc.queryForList("SELECT * FROM user INNER JOIN contract on user.userId = contract.userId"
				+ " WHERE email = ? ORDER BY startDate desc"
				, mailAddress);
		List<User> userList = new ArrayList<>();
		for (Map<String, Object> map : getList) {
            User user = new User();
            // Userインスタンスに取得したデータをセットする
            user.setContractId((int)map.get("contractId"));
            user.setUserId((int)map.get("userId"));
            user.setContractTime((String)map.get("contractTime"));
            user.setStartTime((Time)map.get("startTime"));
            user.setBreakTime((Time)map.get("breakeTime"));
            user.setEndTime((Time)map.get("endTime"));
            user.setStartDate((Date)map.get("startDate"));
    		user.setEndDate((Date)map.get("endDate"));
    		user.setOfficeName((String)map.get("officeName"));
    		userList.add(user);
		}
		return userList;
	}
	
	public List<User> getOnlyContractByUserId(int userId) throws DataAccessException {
		List<Map<String, Object>> getList = jdbc.queryForList("SELECT * FROM contract"
				+ " WHERE userId = ? "
				, userId);
		List<User> userList = new ArrayList<>();
		for (Map<String, Object> map : getList) {
            User user = new User();
            // Userインスタンスに取得したデータをセットする
            user.setContractId((int)map.get("contractId"));
            user.setUserId((int)map.get("userId"));
            user.setStartTime((Time)map.get("startTime"));
            user.setBreakTime((Time)map.get("breakeTime"));
            user.setEndTime((Time)map.get("endTime"));
            user.setStartDate((Date)map.get("startDate"));
    		user.setEndDate((Date)map.get("endDate"));
    		user.setOfficeName((String)map.get("officeName"));
    		userList.add(user);
		}
		return userList;
	}
	
	public void insertIntoContract(User user) throws DataAccessException {
		//1件登録
		jdbc.update("INSERT INTO contract("
				+ " contractId,"
				+ " userId,"
				+ " contractTime,"
				+ " startTime,"
				+ " breakTime,"
				+ " endTime,"
				+ " startDate,"
				+ " endTime,"
				+ " officeName,)"
				+ " VALUES(?, ?, ?, ?, ?, ?)"
				,user.getContractId()
				,user.getUserId()
				,user.getContractTime()
				,user.getStartTime()
				,user.getBreakTime()
				,user.getEndTime()
				,user.getStartDate()
				,user.getEndDate()
				,user.getOfficeName());
	}
	
	private User convert(Map<String, Object> map) {		 
		User user = new User();
        user.setUserId((int)map.get("userId"));
        user.setUserName((String)map.get("userName"));
        user.setEmail((String)map.get("email"));
        user.setPassword((String)map.get("password"));
        user.setRole((int)map.get("role"));
        user.setUserStatus((int)map.get("userStatus"));
    	user.setRequestedAt((String)map.get("requestedAt"));
		return user;
	}
	
}
