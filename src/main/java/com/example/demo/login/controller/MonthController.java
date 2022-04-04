package com.example.demo.login.controller;

import com.example.demo.login.domain.model.Month;
import com.example.demo.login.domain.service.ContractMonthService;
import com.example.demo.login.domain.service.ContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class MonthController {
    private final ContractService contractService;
    private final ContractMonthService contractMonthService;

    @GetMapping("/month/{contractId}")
    public String getContractList(Model model, @PathVariable("contractId") int contractId) {
        String officeName = contractService.getOfficeNameByContractId(contractId).getOfficeName();
        List<Month> monthList = contractMonthService.getMonthListByContractId(contractId);
        List<Map<String, String>> yearMonthList = contractMonthService.getYearMonth(monthList);
        model.addAttribute("OfficeName", officeName);
        model.addAttribute("YearMonthList", yearMonthList);
        return "login/month";
    }
}
