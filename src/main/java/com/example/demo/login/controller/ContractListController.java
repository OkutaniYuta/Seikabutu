package com.example.demo.login.controller;

import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ContractListController {
    private final UserService userService;

    @GetMapping("/contract_list")
    public String getContractList(Model model, @AuthenticationPrincipal UserDetails auth) {
        String email = auth.getUsername();
        List<User> userList = userService.getContractByEmail(email);
        model.addAttribute("UserList", userList);
        return "login/contract_list";
    }
}
