package com.example.demo.login.controller;

import com.example.demo.login.domain.model.ContractInfoForm;
import com.example.demo.login.domain.repository.jdbc.ContractDao;
import com.example.demo.login.domain.service.ContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class HoursChangeController {
    private final ContractService contractService;
    private final ContractDao contractDao;

    @GetMapping("/hours_change")
    public String getHoursChange(@ModelAttribute @Validated ContractInfoForm form, Model model) {
        return "login/hours_change";
    }

    @PostMapping("/hours_change")
    public String postSignUp(@ModelAttribute @Validated ContractInfoForm form, BindingResult bindingResult,
                             Model model, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return getHoursChange(form, model);
        }

        HttpSession session = request.getSession(); // セッションオブジェクトを生成
        int contractId = contractDao.getContractIdByUserId((int) session.getAttribute("userId"));
        contractService.updateContract(contractId, form.getContractTime(), form.getStartTime(), form.getBreakTime(), form.getEndTime());
        return "redirect:/hours_change/";
    }
}
