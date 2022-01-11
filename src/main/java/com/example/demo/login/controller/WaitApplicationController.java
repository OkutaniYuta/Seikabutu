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

@Controller
public class WaitApplicationController {

  //ログイン画面のGETメソッド用処理
    @GetMapping("/waitApplication")
    public String getwaitApplication(Model model) {

        //に画面遷移
        return "signup/waitApplication";
    }

    /**
     * ログイン画面のPOSTメソッド用処理.
     */
    @PostMapping("/waitApplication")
    public String postWaitApplication(Model model) {

        //login.htmlに画面遷移
        return "login";
    }
   
    
   
}