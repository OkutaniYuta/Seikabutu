package com.example.demo.login.controller;

import java.util.Date;

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
		Date startDate = userService.getByContract(mailAddress).getStartDate();
		Date endDate = userService.getByContract(mailAddress).getEndDate();
		String officeName = userService.getByOfficeName(mailAddress).getOfficeName();
		
		model.addAttribute("StartDate", startDate);
		model.addAttribute("EndDate", endDate);
		model.addAttribute("OfficeName", officeName);
		
		return "login/contract_list";
		
	}
	
	
}