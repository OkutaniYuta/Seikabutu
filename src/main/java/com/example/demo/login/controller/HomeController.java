package com.example.demo.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //Principalからログインユーザの情報を取得
        String mailAddress = auth.getName();    
		return "login/home";
	}
	
}