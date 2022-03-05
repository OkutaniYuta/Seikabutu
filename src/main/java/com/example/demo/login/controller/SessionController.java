package com.example.demo.login.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.login.domain.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class SessionController {
	private final UserService userService;
	
	@GetMapping("/session")
    public void getSessionController(@AuthenticationPrincipal UserDetails auth, HttpServletResponse response) throws ServletException, IOException{
		String mailAddress = auth.getUsername();
    	int userStatus = userService.getByUserStatus(mailAddress).getUserStatus();
    	System.out.println(userStatus);
    	if(userStatus == 0) {
    		response.sendRedirect("/adminhome");
    	}else if(userStatus == 1) {
    		response.sendRedirect("/home");
    	}else {
    		response.sendRedirect("/login");
    	}
    	
	}
}
