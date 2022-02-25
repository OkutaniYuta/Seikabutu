package com.example.demo.login.controller;

import java.util.Date;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class WorkingInfoEachMonthController {
	
	@GetMapping("/contract_list")
	public String getContractList(Model model) {
		
		model.addAttribute("StartDate");
	
		return "login/contract_list";
		
	}

}
