package com.example.demo.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.stereotype.Service;

@Service

public class HomeService {
	public String todayObj() {
	Calendar cl = Calendar.getInstance();

	//フォーマットを指定する
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日(E)");
	String nowDate = sdf.format(cl.getTime()).toString();
	
	return nowDate;
	
	}
	
	
	
}