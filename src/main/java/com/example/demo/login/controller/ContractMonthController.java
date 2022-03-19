package com.example.demo.login.controller;

import com.example.demo.login.domain.model.ContractMonth;
import com.example.demo.login.domain.service.ContractMonthService;
import com.example.demo.login.domain.service.ContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class ContractMonthController {
    private final ContractService contractService;

    @GetMapping("/month")
    public String getContractList(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(); // セッションオブジェクトを生成
        int userId = (int) session.getAttribute("userId"); // セッションからuserIdを取得し、userIdに再代入
        int contractId = ContractService.getContractIdByUserId(userId).getContractId();
        List<ContractMonth> contractMonthList = ContractMonthService.getMonthByContractId(contractId);
        model.addAttribute("ContractMonthList", contractMonthList);
        return "login/month";
    }
}
