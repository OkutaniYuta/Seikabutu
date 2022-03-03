package com.example.demo.login.controller;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.login.domain.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class LoginController {
	
	private final UserService userService;
	
    @GetMapping("/login")
    public String getLogin() {
    	
        return "login/login";
    }
//    @PostMapping("/login")
//    public String postLogin(UserDetails auth) {
//    	String mailAddress = auth.getUsername();
//    	int userStatus = userService.getByUserStatus(mailAddress).getUserStatus();
//    	System.out.println(userStatus);
//    	if(userStatus == 0) {
//    		return "login/adminHome";
//    	}
//    	if(userStatus == 1) {
//    		return "login/home";
//    	}
//    	if(userStatus == 2) {
//    		return "redirect:/login";
//    	}
//		return "login/login";
//    }
}
