package com.example.demo.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ContractUpdateController {
    @GetMapping("/contract_update")
    public String getContractUpdate() {
        return "login/contract_update";
    }
}
