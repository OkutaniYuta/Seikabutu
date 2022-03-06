package com.example.demo.login.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.login.domain.model.PasswordChangeForm;
import com.example.demo.login.domain.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class PasswordChangeController {
	private final UserService userService;
	private final PasswordEncoder passwordEncoder;
	
	@GetMapping("/password_change")
	public String getPasswordChange(@ModelAttribute PasswordChangeForm form, Model model) {
		return "login/password_change";
	}

	@PostMapping("/password_change")
	public String postPasswordChange(@ModelAttribute @Validated PasswordChangeForm form, BindingResult bindingResult,
			Model model, @AuthenticationPrincipal UserDetails auth) {
		if(bindingResult.hasErrors()) {
			return "login/password_change";
		}
		String newEncodedPassword = passwordEncoder.encode(form.getNewPassword()); // 「新しいパスワード入力」を暗号化
		userService.updatePassword(newEncodedPassword, auth);
		return "redirect:/password_change";
	}
}
