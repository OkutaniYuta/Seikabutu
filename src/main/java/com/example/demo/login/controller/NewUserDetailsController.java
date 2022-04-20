package com.example.demo.login.controller;

import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class NewUserDetailsController {
    private final UserService userService;

    @GetMapping("/newUserDetails/{userId}")
    public String getAdminHome(@ModelAttribute User form, Model model, @PathVariable("userId") int userId) {
        List<User> userStatus = userService.getUserStatus(userId);
        model.addAttribute("userStatus", userStatus);
        return "login/newUserDetails";
    }
}
