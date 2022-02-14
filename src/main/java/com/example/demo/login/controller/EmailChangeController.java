package com.example.demo.login.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.login.domain.model.SignupForm;
import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.service.UserService;


@Controller
public class EmailChangeController<UserEntity> {
	
	@Autowired
	UserService userService;
	
	@Autowired
	HomeController homeController;
	
	
	
	@GetMapping("/email_change")
	public String getEmailChange(@ModelAttribute SignupForm form, Model model, HttpServletRequest request) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    //Principalからログインユーザの情報を取得
	    String mailAddress = auth.getName();
	    
		int userId = userService.selectByUserId(mailAddress).getUserId();
        
        String userEmail = userService.selectEmail(userId).getEmail();
		
		model.addAttribute("UserEmail", userEmail);
		
		return "login/email_change";
		
	}
	
	@PostMapping("/email_change")
    public String postEmailchange(@ModelAttribute SignupForm form, BindingResult bindingResult,
    		Model model) {
    	
    	
    	
    	User user = new User();
    	
    	user.setEmail(form.getEmail());
    	user.setComfirmEmail(form.getComfirmEmail());
    	
    	if(user.getEmail().equals(user.getComfirmEmail())) {
    		userService.updateByEmail(user);
    	}
    	
    	return "redirect:/email_change";
    	
	}
	
//	   private void updateSecurityContext(UserEntity userEntity) {
//	        UserDetails user = User.builder()
//	                .username(userEntity.getUsername())
//	                .password(userEntity.getPassword())
//	                .roles(userMapper.findRolesByUserId(userEntity.getId()).toArray(String[]::new))
//	                .build();
//	        SecurityContext context = SecurityContextHolder.getContext();
//	        context.setAuthentication(new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities()));
//
//	        logger.info("security context updated to {}", user);
//	    }
	
}