package com.example.demo.login.controller;

import com.example.demo.login.domain.model.WorkTime;
import com.example.demo.login.domain.model.WorkTimeForm;
import com.example.demo.login.domain.service.ContractMonthService;
import com.example.demo.login.domain.service.ContractService;
import com.example.demo.login.domain.service.WorkTimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class MonthDetailsController {
    private final WorkTimeService workTimeService;
    private final ContractService contractService;
    private final ContractMonthService contractMonthService;

    @GetMapping("/monthDetails/{contractId}/{monthId}")
    public String getContractList(Model model, @PathVariable("monthId") int monthId) {
        List<WorkTime> workTimeList = workTimeService.getWorkTimeList(monthId);
        model.addAttribute("workTimeList", workTimeList);
        return "login/monthDetails";
    }

    @PostMapping("/monthDetails/{contractId}/{monthId}")
    public String postWorkTimeInsert(@ModelAttribute WorkTimeForm form, BindingResult bindingResult,
                                     HttpServletRequest request) {
        HttpSession session = request.getSession(); // セッションオブジェクトを生成
        int userId = (int) session.getAttribute("userId");
        int contractId = contractService.getContractIdByUserId(userId);
        int monthId = contractMonthService.getMonthIdByContractId(contractId);

        WorkTime workTime = new WorkTime();
        workTime.setMonthId(monthId);
        workTime.setWorkDay(form.getWorkDay());
        workTime.setStartTime(form.getStartTime());
        workTime.setBreakTime(form.getBreakTime());
        workTime.setEndTime(form.getEndTime());
        workTimeService.insertWorkTime(workTime);

        return "redirect:/workTimeInsert";
    }
}
