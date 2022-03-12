package com.example.demo.login.domain.model;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Data;

@Data
public class Contract {
	//contractテーブル
	private int contractId;
	private int userId;
	private String contractTime;
	private LocalTime startTime;
	private LocalTime breakTime;
	private LocalTime endTime;
	private LocalDate startDate;
	private LocalDate endDate;
	private String officeName;
}