package com.example.demo.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.login.domain.model.SignupForm;
import com.example.demo.login.domain.service.UserService;


@Controller
public class HomeController {
	@Autowired
	UserService userService;
	
	//ホーム画面用のGET用メソッド
	@GetMapping("/home")
	public String getHome(Model model) {
		//コンテンツ部分に画面を表示するための文字列を登録
		model.addAttribute("contents", "login/home :: home_contents");
		return "login/homeLayout";
	}
	
	
	
	//ログアウト用メソッド
	@PostMapping("/logout")
	public String postLogout() {
		//ログイン画面にリダイレクト
		return "redirecr:/login";
	}
	
	//アドミン権限専用のGET用メソッド
//	@GetMapping("/admin")
//	public String getAdmin(Model model) {
//		//コンテンツ部分に新規登録申請一覧を表示させる文字列を登録
//		model.addAttribute("contents", "login/admin :: admin_contents");
//		
//		//レイアウト用テンプレート
//		return "login/home_layout";
//	}
}