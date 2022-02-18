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

import com.example.demo.login.domain.model.SignupForm;
import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.service.UserService;



@Controller
public class PasswordChangeController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@GetMapping("/password_change")
	public String getPasswordChange(@ModelAttribute SignupForm form, Model model) {
		
		return "login/password_change";
		
	}
	
	@PostMapping("/password_change")
    public String postPasswordChange(@ModelAttribute @Validated SignupForm form, BindingResult bindingResult,
    		Model model, @AuthenticationPrincipal UserDetails auth) {
		//入力チェックに引っかかった場合、画面にエラーメッセージを表示する
//    	if(bindingResult.hasErrors()) {
//    		//GETリクエスト用のメソッドを呼び出して、ユーザー画面に戻ります
//    		return getPasswordChange(form, model);
//    	}
		
		String nowPassword = form.getNowPassword();//現在のパスワード入力
		String newPassword = form.getPassword();//新しいパスワード入力
		String confirmPassword = form.getConfirmPassword();//新しいパスワードの確認
		User user = ;// ログインユーザーのメールアドレスをキーにUserテーブルから１行取得する。
		int userId = ;// userからuserIdをゲットする
		String originalEncodedPassword = ; // userからpassword(暗号化されている)をゲットする
		//メールアドレスにユニークキー(DB)をつける
		//現在ログインしているユーザーのパスワードと画面の「現在のパスワード入力」と一致していたら、
		//「新しいパスワード入力」と「新しいパスワードの確認」を比較して、同じなら、アップデートメソッドの引数に「新しいパスワード入力」とログインユーザーのパスワードを渡す
		
		// 以下を満たす場合,DBを更新する
		//    * 現在ログインしているユーザーのパスワードと画面の「現在のパスワード入力」が等しい
		//    * 「新しいパスワード入力」と「新しいパスワードの確認」が等しい
		if (passwordEncoder.matches(nowPassword, originalEncodedPassword) && newPassword.equals(confirmPassword)) {
			userService.updatePassword(newPassword, userId);
		}
		// パスワード変更画面にリダイレクトする
		return "redirect:/password_change";
		/*
		if (!nowPassword.equals(originalPassword) || !newPassword.equals(confirmPassword)) {
			return "redirect:/password_change";
		}
		userService.updatePassword(newPassword, originalPassword);
		return "redirect:/password_change";
		
		if (!nowPassword.equals(originalPassword)) {
			return "redirect:/password_change";
		}
		if (!newPassword.equals(confirmPassword)) {
			return "redirect:/password_change";
		}
		userService.updatePassword(newPassword, originalPassword);
		return "redirect:/password_change";
		
		if(nowPassword.equals(originalPassword)) {
			if(newPassword.equals(confirmPassword)) {
				userService.updatePassword(newPassword, originalPassword);
			} else {
				return "redirect:/password_change";
			}
		} else {
			return "redirect:/password_change";
		}
    	return "redirect:/password_change";
    	*/
	}
	
}