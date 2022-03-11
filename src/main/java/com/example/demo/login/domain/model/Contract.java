package com.example.demo.login.domain.model;

import java.sql.Time;
import java.util.Date;

import lombok.Data;

@Data
public class Contract {
	//contractテーブル
	private int contractId;
	private int userId;
	private String contractTime;
	private Time startTime;
	private Time breakTime;
	private Time endTime;
	private Date startDate;
	private Date endDate;
	private String officeName;
}