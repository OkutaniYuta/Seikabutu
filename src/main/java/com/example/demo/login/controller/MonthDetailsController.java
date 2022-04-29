package com.example.demo.login.controller;

import com.example.demo.login.domain.model.WorkTime;
import com.example.demo.login.domain.model.WorkTimeForm;
import com.example.demo.login.domain.service.WorkTimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class MonthDetailsController {
    private final WorkTimeService workTimeService;

    @GetMapping("/monthDetails/{contractId}/{monthId}")
    public String getMonthDetails(Model model, @PathVariable("monthId") int monthId, @PathVariable("contractId") int contractId, @ModelAttribute WorkTimeForm form) {
        List<WorkTime> workTimeList = workTimeService.getWorkTimeList(monthId);
        model.addAttribute("workTimeList", workTimeList);
        model.addAttribute("contractId", contractId);
        model.addAttribute("monthId", monthId);
        return "login/monthDetails";
    }

    @PostMapping("/monthDetails/{contractId}/{monthId}")
    public String postMonthDetails(@ModelAttribute WorkTimeForm form, BindingResult bindingResult,
                                   @PathVariable("monthId") int monthId, @PathVariable("contractId") int contractId) {

        workTimeService.insertWorkTimeInMonth(monthId, form.getWorkDay(), form.getStartTime(), form.getBreakTime(), form.getEndTime()); // TODO: 2022/04/22 updateメソッドに変更(案件に上がってから)
        return "redirect:/monthDetails/" + contractId + "/" + monthId;
    }

    @PostMapping("/monthDetails/{contractId}/{monthId}/delete")
    public String postDeleteMonthDetails(Model model, @ModelAttribute WorkTimeForm form, @RequestParam("workDay") String workDay,
                                         BindingResult bindingResult, @PathVariable("monthId") int monthId, @PathVariable("contractId") int contractId) {
        LocalDate formatChangeWorkDay = workTimeService.convertStringToLocalDate(workDay);
        workTimeService.deleteWorkTimeInMonthByWorkDay(formatChangeWorkDay);
        return "redirect:/monthDetails/" + contractId + "/" + monthId;
    }
}
