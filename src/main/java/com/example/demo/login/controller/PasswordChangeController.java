package com.example.demo.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.example.demo.login.domain.service.AuthenticationService;
import com.example.demo.login.domain.service.UserService;

@Controller
public class PasswordChangeController {

	@Autowired
	UserService userService;

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	AuthenticationService authenticationService;

	@GetMapping("/password_change")
	public String getPasswordChange(@ModelAttribute PasswordChangeForm form, Model model) {

		return "login/password_change";

	}

	@PostMapping("/password_change")
	public String postPasswordChange(@ModelAttribute @Validated PasswordChangeForm form, BindingResult bindingResult,
			Model model, @AuthenticationPrincipal UserDetails auth) {

		//入力チェックに引っかかった場合、画面にエラーメッセージを表示する
		//    	if(bindingResult.hasErrors()) {
		//    		//GETリクエスト用のメソッドを呼び出して、ユーザー画面に戻ります
		//    		return getPasswordChange(form, model);
		//    	}

		String nowPassword = form.getNowPassword(); // 「現在のパスワード入力」
		String newPassword = form.getNewPassword(); // 「新しいパスワード入力」
		String newEncodedPassword = passwordEncoder.encode(newPassword); // 「新しいパスワード入力」を暗号化
		String confirmPassword = form.getConfirmPassword(); // 「新しいパスワードの確認」
		String mailAddress = auth.getUsername(); // ログインユーザーのmailaddress
		String originalEncodedPassword = auth.getPassword(); // 
		
		userService.updatePassword(nowPassword, originalEncodedPassword, newPassword, confirmPassword, mailAddress, newEncodedPassword, auth);
		
		// パスワード変更画面にリダイレクトする
		return "redirect:/password_change";

	}
}