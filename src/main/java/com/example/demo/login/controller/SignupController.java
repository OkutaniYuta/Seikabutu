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


@Controller
public class SignupController {

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
    public String postSignUp(@ModelAttribute SignupForm form, BindingResult bindingResult, Model model) {
    	if(bindingResult.hasErrors()) {
    		//GETリクエスト用のメソッドを呼び出して、ユーザー画面に戻ります
    		return getSignUp(form, model);
    	}
    	
    	//formの中身をコンソールに出して確認します
    	System.out.println(form);
    	
        //waitApplicationに画面遷移
        return "login/waitApplication";
    }
   
    
   
}