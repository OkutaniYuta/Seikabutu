package com.example.demo.login.domain.model;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class WorkTime {
    private int workTimeId;
    private int monthId;
    private LocalDate workDay;
    private LocalTime startTime;
    private LocalTime breakTime;
    private LocalTime endTime;
    private int workTimeMinute;
}
