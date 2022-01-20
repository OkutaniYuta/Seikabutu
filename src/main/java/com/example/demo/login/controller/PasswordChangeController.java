package com.example.demo.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.login.domain.model.SignupForm;



@Controller
public class PasswordChangeController {
	
	@GetMapping("/password_change")
	public String getPasswordChange(@ModelAttribute SignupForm form, Model model) {
		
		return "login/password_change";
		
	}
	
	@PostMapping("/password_change")
    public String postPasswordChange(@ModelAttribute @Validated SignupForm form, BindingResult bindingResult,
    		Model model) {
    	
    	//入力チェックに引っかかった場合、ユーザー登録画面に戻る
    	if(bindingResult.hasErrors()) {
    		//GETリクエスト用のメソッドを呼び出して、ユーザー画面に戻ります
    		return getPasswordChange(form, model);
    	}
    	
    	return "redirect:/password_change";
    	
	}
	
}