package com.example.demo.login.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.login.domain.service.UserService;
import com.example.demo.service.HomeService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class HomeController {
	private final HomeService homeService;
	private final UserService userService;
	//ホーム画面用のGET用メソッド
	@GetMapping("/home")
	public String getHome(Model model) {
		String nowDate = homeService.todayObj();
		model.addAttribute("HomeService", nowDate);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //Principalからログインユーザの情報を取得
        String mailAddress = auth.getName();
        String officeName = userService.getByOfficeName(mailAddress).getOfficeName();
        model.addAttribute("OfficeName", officeName);
		return "login/home";
	}
}
