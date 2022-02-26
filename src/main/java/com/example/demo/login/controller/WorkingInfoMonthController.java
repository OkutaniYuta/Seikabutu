package com.example.demo.login.controller;

import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

public class WorkingInfoMonthController {
	
	@GetMapping("/contract_list")
	public String getContractList(@ModelAttribute Model model) {
		return "login/contract_list";
	}
	
	@PostMapping
	public String postContractList(@Validated @ModelAttribute Model model) {
		return "login/contract_list";
	}

}
