package com.example.demo.login.controller;

import com.example.demo.login.domain.model.ContractInfoForm;
import com.example.demo.login.domain.service.ContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class ContractUpdateController {
    private final ContractService contractService;

    @GetMapping("/contract_update")
    public String getContractUpdate(Model model, @ModelAttribute ContractInfoForm form, HttpServletRequest request) {
        HttpSession session = request.getSession(); // セッションオブジェクトを生成
        int userId = (int) session.getAttribute("userId"); // !動作確認用!セッションからuserIdを取得し、userIdに再代入
        String officeName = contractService.getOfficeNameByUserId(userId);
        model.addAttribute("officeName", officeName);
        return "login/contract_update";
    }

    @PostMapping("/contract_update")
    public String postContractUpdate(Model model, @ModelAttribute ContractInfoForm form, BindingResult bindingResult,
                                     HttpServletRequest request) {

        HttpSession session = request.getSession(); // セッションオブジェクトを生成
        int userId = (int) session.getAttribute("userId");
        contractService.updateEndDate(userId, form.getEndDate());
        return "redirect:/contract_update";
    }
}
