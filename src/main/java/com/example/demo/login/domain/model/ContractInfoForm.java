package com.example.demo.login.domain.model;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ContractInfoForm {	
	private String contractTime;
	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime startTime;
	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime breakTime;
	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime endTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate startDate;
	private String officeName;
	
//	@AssertTrue(message = "一緒にして")
//	public boolean isPasswordEqual() {
//	    return Objects.equals(newPassword, confirmPassword);
//	}
}
