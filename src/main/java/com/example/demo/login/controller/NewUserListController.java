package com.example.demo.login.controller;

import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class NewUserListController {
    private final UserService userService;

    @GetMapping("/newUser")
    public String getAdminHome(@ModelAttribute User form, Model model) {
        List<User> userStatusList = userService.getUserStatusList();
        model.addAttribute("userStatusList", userStatusList);
        return "login/newUser";
    }
}
