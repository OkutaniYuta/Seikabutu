package com.example.demo.login.controller;

import com.example.demo.login.domain.service.ContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class ContractUpdateController {
    private final ContractService contractService;

    @GetMapping("/contract_update")
    public String getContractUpdate(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(); // セッションオブジェクトを生成
        int userId = (int) session.getAttribute("userId"); // !動作確認用!セッションからuserIdを取得し、userIdに再代入
        String officeName = contractService.getOfficeNameByUserId(userId).getOfficeName();
        model.addAttribute("OfficeName", officeName);
        return "login/contract_update";
    }
}
