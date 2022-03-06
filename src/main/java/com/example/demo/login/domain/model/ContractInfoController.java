package com.example.demo.login.domain.model;

import java.sql.Time;
import java.util.Date;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class ContractInfoController {	
	@NotBlank
	private int contractId;
	@NotBlank
	private Time contractTime;
	@NotBlank
	private Time startTime;
	@NotBlank
	private Time breakeTime;
	@NotBlank
	private Time endTime;
	@NotBlank
	private Date starDate;
	@NotBlank
	private Date endDate;
	@NotBlank
	private String officeName;
	
//	@AssertTrue(message = "一緒にして")
//	public boolean isPasswordEqual() {
//	    return Objects.equals(newPassword, confirmPassword);
//	}
}
