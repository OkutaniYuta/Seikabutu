package com.example.demo.login.domain.service;

import com.example.demo.login.domain.model.Contract;
import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.repository.jdbc.ContractDao;
import com.example.demo.login.domain.repository.jdbc.UserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserDao dao;
    private final ContractDao contractDao;
    private final AuthenticationService authenticationService;

    //insert用メソッド
    public void insert(User user) {
        //insert実行
        dao.insert(user);
    }

    public User getEmailByEmail(String email) {
        return dao.getEmailByEmail(email);
    }

    //メールアドレスUpdate用メソッド
    public void updateEmail(String newEmail, String originalEmail, UserDetails auth) {
        dao.updateEmail(newEmail, originalEmail);
        authenticationService.changeEmail(newEmail, auth);
    }

    public void updateUserStatus(int userId) {
        dao.updateUserStatus(userId);
    }

    public void updateUserBlocking(int userId) {
        dao.updateUserBlocking(userId);
    }

    public List<User> getUserStatusList() {
        return dao.getUserStatusList();
    }

    public List<User> getUserStatus(int userId) {
        return dao.getUserStatus(userId);
    }


    //パスワード更新用メソッド
    public void updatePassword(String newEncodedPassword, UserDetails auth) {
        String originalEmail = auth.getUsername();
        dao.updatePassword(newEncodedPassword, originalEmail);
        authenticationService.changePassword(newEncodedPassword, auth);
    }

    //コントラクトテーブルの情報のみを取得
    public List<Contract> getOnlyContractByUserId(int userId) {
        return contractDao.getOnlyContractByUserId(userId);
    }
}
