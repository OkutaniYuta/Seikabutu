package com.example.demo.login.controller;

import com.example.demo.login.domain.model.WorkTime;
import com.example.demo.login.domain.model.WorkTimeForm;
import com.example.demo.login.domain.service.WorkTimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class MonthDetailsController {
    private final WorkTimeService workTimeService;

    @GetMapping("/monthDetails/{contractId}/{monthId}")
    public String getMonthDetails(Model model, @PathVariable("monthId") int monthId) {
        List<WorkTime> workTimeList = workTimeService.getWorkTimeList(monthId);
        model.addAttribute("workTimeList", workTimeList);
        return "login/monthDetails";
    }

    @PostMapping("/monthDetails/{contractId}/{monthId}/insert")
    public String postMonthDetails(@ModelAttribute WorkTimeForm form, BindingResult bindingResult,
                                   @PathVariable("monthId") int monthId, @PathVariable("contractId") int contractId) {

        WorkTime workTime = new WorkTime();
        workTime.setMonthId(monthId);
        workTime.setWorkDay(form.getWorkDay());
        workTime.setStartTime(form.getStartTime());
        workTime.setBreakTime(form.getBreakTime());
        workTime.setEndTime(form.getEndTime());
        workTimeService.insertWorkTime(workTime);

        return "redirect:/monthDetails";
    }
}
