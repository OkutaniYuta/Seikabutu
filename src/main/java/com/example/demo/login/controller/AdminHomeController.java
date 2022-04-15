package com.example.demo.login.controller;

import com.example.demo.login.domain.service.ContractService;
import com.example.demo.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@Controller
public class AdminHomeController {
    private final HomeService homeService;
    private final ContractService contractService;

    //ホーム画面用のGET用メソッド
    @GetMapping("/adminHome")
    public String getAdminHome(Model model, HttpServletRequest request) {

        return "login/adminHome";
    }
}
