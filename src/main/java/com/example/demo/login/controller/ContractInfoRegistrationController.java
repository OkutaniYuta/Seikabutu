package com.example.demo.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContractInfoRegistrationController {
	@GetMapping("/contract_info_registration")
	public String getContractInfoRagistration() {
		return "login/contract_info_registration";
	}
}
