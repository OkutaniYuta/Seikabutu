package com.example.demo.login.controller;

import com.example.demo.login.domain.model.WorkTimeForm;
import com.example.demo.login.domain.service.ContractMonthService;
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
public class WorkTimeInsertController {
    private final ContractService contractService;
    private final ContractMonthService contractMonthService;

    @GetMapping("/workTimeInsert")
    public String getWorkTimeInsert(@ModelAttribute WorkTimeForm form, Model model) {
        return "login/workTimeInsert";
    }

    @PostMapping("/contractInsert")
    public String postSignUp(@ModelAttribute WorkTimeForm form, BindingResult bindingResult,
                             Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(); // セッションオブジェクトを生成
        int userId = (int) session.getAttribute("userId"); // !動作確認用!セッションからuserIdを取得し、userIdに再代入
        int contractId = contractService.getContractIdByUserId(userId).getContractId();
        int monthId = contractMonthService.getMonthIdByContractId(contractId).getMonthId();

        return "login/workTimeInsert";
    }
}
