package com.example.demo.login.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class SessionController {
	private final UserService userService;
	
	@GetMapping("/session")
    public void getSessionController(@AuthenticationPrincipal UserDetails auth, HttpServletResponse response,
    		HttpServletRequest request) throws ServletException, IOException{
		String mailAddress = auth.getUsername();
		int role = userService.getByUserStatus(mailAddress).getRole();
		int userStatus = userService.getByUserStatus(mailAddress).getUserStatus();
    	int userId = userService.getByUserStatus(mailAddress).getUserId();
    	HttpSession session = request.getSession(); // セッションオブジェクトを生成
    	session.setAttribute("userId", userId); // セッションにuserIdを登録
    	List<User> userList = userService.getByOnlyContract(userId);// !ここに書く事!UserIdをキーにコントラクトテーブルのみを全件リスト型で取得
    	
    	if(role == 0) {
    		response.sendRedirect("/adminhome");
    	}else if(userStatus == 1 && userList.size() >= 1) {//　&&　コントラクトリストのみをListで取得して、List sizeが1以上なら、Home画面に遷移する。
    		response.sendRedirect("/home");
    	}else {
    		response.sendRedirect("/login");
    	}
	}
}
