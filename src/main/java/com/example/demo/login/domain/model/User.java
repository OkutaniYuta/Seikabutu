package com.example.demo.login.domain.model;

import lombok.Data;

@Data
public class User {

    //Userテーブル
    private int userId;
    private String userName;
    private String password;
    private String comfirmPassword;
    private String email;
    private String comfirmEmail;
    private int role;
    private int userStatus;
    private String requestedAt;

}
