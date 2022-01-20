package com.example.demo.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ContractListController {
	@GetMapping("/contract_list")
	public String getContractList() {
		
		return "login/contract_list";
		
	}
	
	
}