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
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class ContractMonthController {
    private final ContractService contractService;
    private final ContractMonthService contractMonthService;

    @GetMapping("/month")
    public String getContractList(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(); // セッションオブジェクトを生成
        int userId = (int) session.getAttribute("userId"); // セッションからuserIdを取得し、userIdに再代入
        int contractId = contractService.getContractIdByUserId(userId).getContractId();
        String officeName = contractService.getOfficeNameByContractId(contractId).getOfficeName();

        List<ContractMonth> contractMonthList = contractMonthService.getMonthByContractId(contractId);
        List<Map<String, String>> yearMonth = contractMonthService.getYearMonth(contractMonthList);

        model.addAttribute("OfficeName", officeName);
        model.addAttribute("YearMonth", yearMonth);
        return "login/month";
    }
}
