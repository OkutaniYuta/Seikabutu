package com.example.demo.service;

import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@Service


public class HomeService {
    public String todayObj() {
        Calendar cl = Calendar.getInstance();

        //フォーマットを指定する
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日(E)");
        String nowDate = sdf.format(cl.getTime());
        return nowDate;
    }

}