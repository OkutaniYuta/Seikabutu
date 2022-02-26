package com.example.demo.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.login.domain.model.EmailChangeForm;
import com.example.demo.login.domain.service.UserService;

@Controller
public class EmailChangeController {

	@Autowired
	UserService userService;
	
	@GetMapping("/email_change")
	public String getEmailChange(@ModelAttribute EmailChangeForm form, Model model, @AuthenticationPrincipal UserDetails auth) {
		//Principalからログインユーザの情報を取得
		String mailAddress = auth.getUsername();
		model.addAttribute("UserEmail", mailAddress);
		return "login/email_change";
	}

	@PostMapping("/email_change")
	public String postEmailChange(@Validated @ModelAttribute EmailChangeForm form, BindingResult bindingResult, Model model, @AuthenticationPrincipal UserDetails auth) {
		if(bindingResult.hasErrors()) {
			return "login/email_change";
		}
		String originalEmail = auth.getUsername();
		userService.updateEmail(form.getEmail(), originalEmail, auth);
		return "redirect:/email_change";
	}
}