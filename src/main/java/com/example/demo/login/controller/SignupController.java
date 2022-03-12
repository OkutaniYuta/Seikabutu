package com.example.demo.login.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.login.domain.model.SignupForm;
import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class SignupController {
	
	private final UserService userService;
	
    @GetMapping("/signup")
    public String getSignUp(@ModelAttribute SignupForm form, Model model) {
        return "login/signup";
    }

    @PostMapping("/signup")
    public String postSignUp(@ModelAttribute @Validated SignupForm form, BindingResult bindingResult,
    		Model model) { 	
    	if(bindingResult.hasErrors()) {
    		return getSignUp(form, model);
    	}
    	Calendar cl = Calendar.getInstance();
    	//フォーマットを指定する
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	String nowDate = sdf.format(cl.getTime()).toString();
    	//insert用変数
    	User user = new User();
		user.setUserName(form.getUserName());
		user.setEmail(form.getEmail());
		user.setPassword(form.getPassword());
		user.setRole(1);
		user.setUserStatus(1);
		user.setRequestedAt(nowDate);
		userService.insert(user);
        return "login/waitApplication";
    }  
}
