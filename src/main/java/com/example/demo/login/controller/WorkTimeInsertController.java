package com.example.demo.login.controller;

import com.example.demo.login.domain.model.WorkTimeForm;
import com.example.demo.login.domain.service.WorkTimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class WorkTimeInsertController {
    private final WorkTimeService workTimeService;

    @GetMapping("/workTimeInsert")
    public String getWorkTimeInsert(@ModelAttribute WorkTimeForm form) {
        return "login/workTimeInsert";
    }

    @PostMapping("/workTimeInsert")
    public String postWorkTimeInsert(@ModelAttribute WorkTimeForm form, BindingResult bindingResult,
                                     HttpServletRequest request) {
        HttpSession session = request.getSession(); // セッションオブジェクトを生成
        int userId = (int) session.getAttribute("userId");
        workTimeService.insertWorkTime(userId, form.getWorkDay(), form.getStartTime(), form.getBreakTime(), form.getEndTime());

        return "redirect:/workTimeInsert";
    }
}
