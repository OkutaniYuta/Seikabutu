package com.example.demo.login.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.login.domain.model.SignupForm;
import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class ContractInfoRegistrationController {
	private final UserService userService;
	
	@GetMapping("/contract_info_registration")
	public String getContractInfoRagistration(@ModelAttribute SignupForm form, Model model) {
		return "login/contract_info_registration";
	}
	
	@PostMapping("/contract_info_registration")
    public String postSignUp(@ModelAttribute @Validated SignupForm form, BindingResult bindingResult,
    		Model model) { 	
    	if(bindingResult.hasErrors()) {
    		return getContractInfoRagistration(form, model);
    	}
    	Calendar cl = Calendar.getInstance();
    	//フォーマットを指定する
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	String nowDate = sdf.format(cl.getTime()).toString();
    	//insert用変数
    	User user = new User();
		user.setUserName(form.getUserName());
		user.setEmail(form.getEmail());
		user.setPassword(form.getPassword());
		user.setRole(1);
		user.setUserStatus(1);
		user.setReqestedAt(nowDate);
		userService.insert(user);
        return "login/waitApplication";
    }  
}
