package com.example.demo.login.domain.model;

import java.sql.Time;
import java.util.Date;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class ContractInfoForm {	
	private String contractTime;
	private Time startTime;
	private Time breakTime;
	private Time endTime;
	private Date startDate;
	private String officeName;
	
//	@AssertTrue(message = "一緒にして")
//	public boolean isPasswordEqual() {
//	    return Objects.equals(newPassword, confirmPassword);
//	}
}
