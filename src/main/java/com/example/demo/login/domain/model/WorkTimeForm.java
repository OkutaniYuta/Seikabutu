package com.example.demo.login.domain.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class WorkTimeForm {
    private int workTimeId;
    private int monthId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate workDay;
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime startTime;
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime breakTime;
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime endTime;
    private int workTimeMinute;//保留
}
