package com.example.demo.login.controller;

import com.example.demo.login.domain.service.ContractService;
import com.example.demo.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class HomeController {
    private final HomeService homeService;
    private final ContractService contractService;

    //ホーム画面用のGET用メソッド
    @GetMapping("/home")
    public String getHome(Model model, HttpServletRequest request) {
        String nowDate = homeService.todayObj();
        model.addAttribute("NowDate", nowDate);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        HttpSession session = request.getSession(); // セッションオブジェクトを生成
        int userId = (int) session.getAttribute("userId"); // !動作確認用!セッションからuserIdを取得し、userIdに再代入
        String officeName = contractService.getOfficeNameByUserId(userId);
        model.addAttribute("OfficeName", officeName);
        return "login/home";
    }
}
