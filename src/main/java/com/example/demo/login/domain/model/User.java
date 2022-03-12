package com.example.demo.login.domain.model;

import java.sql.Time;
import java.util.Date;

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
	
	//contractテーブル
	// TODO 31行目までを消す。
	private int contractId;
	private String contractTime;
	private Time startTime;
	private Time breakTime;
	private Time endTime;
	private Date startDate;
	private Date endDate;
	private String officeName;
}
