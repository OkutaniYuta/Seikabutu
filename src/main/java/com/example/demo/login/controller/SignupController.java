package com.example.demo.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import com.example.demo.login.domain.model.SignupForm;
import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.service.UserService;


@Controller
public class SignupController {
	
	@Autowired
	private UserService userService;

  //ログイン画面のGETメソッド用処理
    @GetMapping("/signup")
    public String getSignUp(@ModelAttribute SignupForm form, Model model) {

        //signupに画面遷移
        return "login/signup";
    }

    /**
     * ログイン画面のPOSTメソッド用処理.
     */
    @PostMapping("/signup")
    public String postSignUp(@ModelAttribute @Validated SignupForm form, BindingResult bindingResult,
    		Model model) {
    	
    	//入力チェックに引っかかった場合、ユーザー登録画面に戻る
    	if(bindingResult.hasErrors()) {
    		//GETリクエスト用のメソッドを呼び出して、ユーザー画面に戻ります
    		return getSignUp(form, model);
    	}
    	
    	//formの中身をコンソールに出して確認します 
    	System.out.println(form);
    	
    	//insert用変数
    	User user = new User();
    	
    	user.setUserId(111);
		user.setUserName(form.getUserName());
		user.setEmail(form.getEmail());
		user.setPassword(form.getPassword());
		user.setRole("ROLE_GENERAL");
		user.setUserStatus(1);
		user.setReqestedAt(1);
		
		//ユーザー登録処理
		boolean result = userService.insert(user);
		
		//ユーザー登録結果の判定
		if(result == true) {
			System.out.println("insert成功");
		} else {
			System.out.println("insert失敗");
		}
    	
        //waitApplicationに画面遷移
        return "redirect:/waitApplication";
        
    }
    
   
   
    
   
}