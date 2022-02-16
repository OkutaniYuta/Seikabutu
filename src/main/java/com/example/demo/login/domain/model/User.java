package com.example.demo.login.domain.model;

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
	private String reqestedAt;
	
	
	//contractテーブル
	private int contractId;
	private int contractTime;
	private int startTime;
	private int brakeTime;
	private int endTime;
	private Date startDate;
	private String officeName;
	public static Object builder() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
	
}