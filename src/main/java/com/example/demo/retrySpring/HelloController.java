package com.example.demo.retrySpring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
	@GetMapping("/hello")
	public String getHello() {
		//hello.htmlに画面遷移
		return "こんにちわ";
	}
}
