package com.example.demo.login.controller;

import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class NewUserDetailsController {
    private final UserService userService;

    @GetMapping("/newUserDetails/{userId}")
    public String getNewUserDetails(@ModelAttribute User form, Model model, @PathVariable("userId") int userId) {
        User user = userService.getUserByUserId(userId);
        model.addAttribute("user", user);
        return "login/newUserDetails";
    }

    @PostMapping("/newUserDetails/{userId}")
    public String postNewUserDetails(Model model, @ModelAttribute User form, @PathVariable("userId") int userId) {
        userService.updateUserStatus(userId);
        return "redirect:/newUserDetails/{userId}";
    }

    @PostMapping("/newUserDetails/{userId}/block")
    public String postNewUserBlock(Model model, @ModelAttribute User form, @PathVariable("userId") int userId) {
        userService.updateUserBlocking(userId);
        return "redirect:/newUserDetails/{userId}";
    }
}
