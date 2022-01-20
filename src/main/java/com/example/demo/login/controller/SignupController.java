package com.example.demo.login.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.example.demo.service.HomeService;


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
		user.setUserStatus(0);
		user.setReqestedAt(nowDate);
		
		//ユーザー登録処理
		userService.insert(user);
		
		
    	
        //waitApplicationに画面遷移
        return "login/waitApplication";
        
    }
    
   
   
    
   
}