package com.example.demo.login.domain.model;

import java.util.Date;
import lombok.Data;

@Data
public class User {
	private int userId;
	private String userName;
	private String password;
	private String email;
	private String role;
	private int userStatus;
	private int reqestedAt;
	
	//contractテーブル
//	private int contractId;
//	private int contractTime;
//	private int startTime;
//	private int brakeTime;
//	private int endTime;
//	private Date startDate;
//	private String officeName;
}