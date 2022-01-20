package com.example.demo.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.service.HomeService;


@Controller
public class HomeController {
	
	@Autowired
	HomeService homeService;
	
	//ホーム画面用のGET用メソッド
	@GetMapping("/home")
	public String getHome(Model model) {
		
		String nowDate = homeService.todayObj();
		
		model.addAttribute("HomeService", nowDate);
		
		
		return "login/home";
	}
	
	
	
//	@PostMapping("/home")
//	public String postLogout() {
//		//ログイン画面にリダイレクト
//		return "redirecr:/email_change";
//	}
	
}