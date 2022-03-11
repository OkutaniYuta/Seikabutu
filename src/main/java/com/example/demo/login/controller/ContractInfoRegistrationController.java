package com.example.demo.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.login.domain.model.ContractInfoForm;
import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class ContractInfoRegistrationController {
	private final UserService userService;
	
	@GetMapping("/contract_info_registration")
	public String getContractInfoRagistration(@ModelAttribute ContractInfoForm form, Model model) {
		return "login/contract_info_registration";
	}
	
	@PostMapping("/contract_info_registration")
    public String postSignUp(@ModelAttribute @Validated ContractInfoForm form, BindingResult bindingResult,
    		Model model) { 	
    	if(bindingResult.hasErrors()) {
    		return getContractInfoRagistration(form, model);
    	}
    	
    	
    	User user = new User();
		user.setContractId(form.getContractId());
		user.setContractTime(form.getContractTime());
		user.setStartTime(form.getStartTime());
		user.setBreakTime(form.getBreakTime());
		user.setEndTime(form.getEndTime());
		user.setStartDate(form.getStartDate());
		user.setEndDate(form.getEndDate());
		user.setOfficeName(form.getOfficeName());
		userService.insert(user);
        return "login/waitApplication";
    }
}
