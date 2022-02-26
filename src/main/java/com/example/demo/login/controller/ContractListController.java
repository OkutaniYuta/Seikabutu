package com.example.demo.login.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.service.UserService;


@Controller
public class ContractListController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/contract_list")
	public String getContractList(Model model, @AuthenticationPrincipal UserDetails auth) {
		String mailAddress = auth.getUsername();
		List<User> userList = userService.getByContract(mailAddress);
		model.addAttribute("UserList", userList);	
		return "login/contract_list";
	}
}