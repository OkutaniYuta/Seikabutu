package com.example.demo.login.controller;

import com.example.demo.login.domain.model.ContractInfoForm;
import com.example.demo.login.domain.repository.jdbc.ContractDao;
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
    private final ContractDao contractDao;

    @GetMapping("/contract_update")
    public String getContractUpdate(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(); // セッションオブジェクトを生成
        int userId = (int) session.getAttribute("userId"); // !動作確認用!セッションからuserIdを取得し、userIdに再代入
        String officeName = contractService.getOfficeNameByUserId(userId).getOfficeName();
        model.addAttribute("OfficeName", officeName);
        return "login/contract_update";
    }

    @PostMapping("/contract_update")
    public String postContractUpdate(Model model, @ModelAttribute ContractInfoForm form, BindingResult bindingResult,
                                     HttpServletRequest request) {

        HttpSession session = request.getSession(); // セッションオブジェクトを生成
        int contractId = contractDao.getContractIdByUserId((int) session.getAttribute("userId"));
        contractService.updateEndDateByContract(contractId, form.getEndDate());
        return "redirect:/contract_update/";
    }
}
