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
		
		return "login/home";
	}
	
//	@RequestMapping("/home")
//    private String init(Model model) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        //Principalからログインユーザの情報を取得
//        String userId = auth.getUserId();
//        model.addAttribute("userId", userId);
//        return "home";
//
//    }

	@RequestMapping("/home")
    private String init(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //Principalからログインユーザの情報を取得
        String userName = auth.getName();
        model.addAttribute("userName", userName);
        
        System.out.println(userName);
        return "login/home";
        
        
	}
	
}