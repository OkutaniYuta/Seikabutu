package com.example.demo.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.login.domain.model.EmailChangeForm;
import com.example.demo.login.domain.service.UserService;


@Controller
public class EmailChangeController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	HomeController homeController;
	
	
	
	@GetMapping("/email_change")
	public String getEmailChange(@ModelAttribute EmailChangeForm form, Model model, @AuthenticationPrincipal UserDetails auth) {
		
	    //Principalからログインユーザの情報を取得
	    String mailAddress = auth.getUsername();
		
		model.addAttribute("UserEmail", mailAddress);
		
		return "login/email_change";
		
	}
	
	@PostMapping("/email_change")
	public String postEmailchange(@ModelAttribute EmailChangeForm form, Model model, @AuthenticationPrincipal UserDetails auth) {
    	String originalEmail = auth.getUsername();
    	String newEmail = form.getEmail();
    	String confirmEmail = form.getConfirmEmail();
		
    	if(newEmail.equals(confirmEmail)) {
    		userService.updateEmail(newEmail, originalEmail);
    		org.springframework.security.core.userdetails.User updated = new org.springframework.security.core.userdetails.User(
                    form.getEmail(),
                    auth.getPassword(),
                    auth.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(
                    new UsernamePasswordAuthenticationToken(updated,
                                                            updated.getPassword(),
                                                            updated.getAuthorities()));
    	}
    	
    	
    	
    	return "redirect:/email_change";
    	
	}
	
	  
	
}