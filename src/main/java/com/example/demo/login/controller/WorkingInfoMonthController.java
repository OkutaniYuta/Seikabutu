package com.example.demo.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class WorkingInfoMonthController {
	@GetMapping("/working_info_month")
	public String getWorkingInfoMonth() {
		return "login/working_info_month";
	}
}
