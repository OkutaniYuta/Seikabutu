package com.example.demo.login.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.login.domain.model.Contract;
import com.example.demo.login.domain.model.ContractInfoForm;
import com.example.demo.login.domain.service.ContractService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class ContractInfoRegistrationController {
	private final ContractService contractService;
	
	@GetMapping("/contract_info_registration")
	public String getContractInfoRagistration(@ModelAttribute ContractInfoForm form, Model model) {
		return "login/contract_info_registration";
	}
	
	@PostMapping("/contract_info_registration")
    public String postSignUp(@ModelAttribute @Validated ContractInfoForm form, BindingResult bindingResult,
    		Model model, HttpServletRequest request) { 	
    	if(bindingResult.hasErrors()) {
    		return getContractInfoRagistration(form, model);
    	}
    	
    	HttpSession session = request.getSession(); // セッションオブジェクトを生成
    	int userId = (int)session.getAttribute("userId"); // !動作確認用!セッションからuserIdを取得し、userIdに再代入
    	
    	Contract contract = new Contract();
    	contract.setUserId(userId);
		contract.setContractTime(form.getContractTime());
		contract.setStartTime(form.getStartTime());
		contract.setBreakTime(form.getBreakTime());
		contract.setEndTime(form.getEndTime());
		contract.setStartDate(form.getStartDate());
		contract.setOfficeName(form.getOfficeName());
		contractService.insertContract(contract);
        return "login/home";
    }
}
