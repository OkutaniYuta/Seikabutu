package com.example.demo.login.domain.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class WorkTimeForm {
    private int workTimeId;
    private int monthId;
    private LocalDate workDay;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime startTime;
    @DateTimeFormat(pattern = "hh:mm:ss")
    private LocalTime breakTime;
    @DateTimeFormat(pattern = "HH:mm")
    private LocalDateTime endTime;
    @DateTimeFormat(pattern = "hh:mm:ss")
    private int workTimeMinute;
}
