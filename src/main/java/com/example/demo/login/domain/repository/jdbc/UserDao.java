package com.example.demo.login.domain.repository.jdbc;

import com.example.demo.login.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
                , user.getUserName()
                , user.getEmail()
                , password
                , user.getRole()
                , user.getUserStatus()
                , user.getRequestedAt());
    }

    // メールアドレス更新用メソッド
    public void updateEmail(String newEmail, String originalEmail) throws DataAccessException {
        jdbc.update("UPDATE user"
                        + " SET"
                        + " email = ?"
                        + " WHERE email = ?"
                , newEmail
                , originalEmail);
    }

    // パスワード更新用メソッド
    public void updatePassword(String newPassword, String mailAddress) throws DataAccessException {
        jdbc.update("UPDATE user"
                        + " SET"
                        + " password = ?"
                        + " WHERE email = ?"
                , newPassword
                , mailAddress);
    }

    public User getEmailByEmail(String email) throws DataAccessException {
        Map<String, Object> map = jdbc.queryForMap("SELECT * FROM user "
                + " WHERE email = ?", email);
        User convert = convert(map);
        return convert;
    }

    public List<User> getContractByEmail(String email) throws DataAccessException {
        List<Map<String, Object>> getList = jdbc.queryForList("SELECT * FROM user INNER JOIN contract on user.userId = contract.userId"
                        + " WHERE email = ? ORDER BY startDate desc"
                , email);

        List<User> userList = new ArrayList<>();
        for (Map<String, Object> map : getList) {
            User user = convert(map);
            userList.add(user);
        }
        return userList;
    }

    private User convert(Map<String, Object> map) {
        User user = new User();
        user.setUserId((int) map.get("userId"));
        user.setUserName((String) map.get("userName"));
        user.setEmail((String) map.get("email"));
        user.setPassword((String) map.get("password"));
        user.setRole((int) map.get("role"));
        user.setUserStatus((int) map.get("userStatus"));
        user.setRequestedAt((String) map.get("requestedAt"));
        return user;
    }

}
