package com.example.demo.login.domain.repository.jdbc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.repository.UserDao;
import com.example.demo.login.domain.service.UserService;

@Repository
public class UserDaoJdbcImpl implements UserDao {

	@Autowired
	JdbcTemplate jdbc;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	UserService userService;

	// Userテーブルに1件insert
	@Override
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
				,user.getReqestedAt());
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

	
	public User getByOfficeName(String mailAddress) throws DataAccessException {
		Map<String, Object> map = jdbc.queryForMap("SELECT * FROM user INNER JOIN contract on user.userId = contract.userId"
				+ " WHERE email = ? ORDER BY startDate desc limit 1" 
				, mailAddress); // 勤務開始日(startDate)を降順(desc)で並び替え、一番上のものをとる。つまり最新(現在契約中)の会社を指定できる
		User user = new User(); // 結果返却用の変数
		user.setOfficeName((String)map.get("officeName")); // 取得したデータを結果返却用の変数にセット
		return user;
	}
	
	public List<User> getByContrac(String mailAddress) throws DataAccessException {
		List<Map<String, Object>> getList = jdbc.queryForList("SELECT * FROM user INNER JOIN contract on user.userId = contract.userId"
				+ " WHERE email = ?"
				, mailAddress);
		List<User> userList = new ArrayList<>();
		for (Map<String, Object> map : getList) {
            User user = new User();
            // Userインスタンスに取得したデータをセットする
            user.setStartDate((Date)map.get("startDate"));
    		user.setEndDate((Date)map.get("endDate"));
    		user.setOfficeName((String)map.get("officeName"));
    		userList.add(user);
		}
		return userList;
	}
}
