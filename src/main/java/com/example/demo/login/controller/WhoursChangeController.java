package com.example.demo.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class WhoursChangeController {
	@GetMapping("/whours_change")
	public String getWhoursChange() {
		
		return "login/whours_change";
		
	}
	
	
}