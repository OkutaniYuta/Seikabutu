package com.example.demo.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.login.domain.service.UserService;


@Controller
public class ContractListController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/contract_list")
	public String getContractList(Model model, @AuthenticationPrincipal UserDetails auth) {	
		String mailAddress = auth.getUsername();
		model.addAttribute("StartDate", userService.getByContract(mailAddress).getStartDate());
		model.addAttribute("EndDate", userService.getByContract(mailAddress).getEndDate());
		model.addAttribute("OfficeName", userService.getByOfficeName(mailAddress).getOfficeName());	
		return "login/contract_list";
	}
}