package com.shift.support.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.shift.support.form.LoginForm;
import com.shift.support.mapper.EmployeeMapper;

import lombok.RequiredArgsConstructor;



@Controller
@RequiredArgsConstructor
public class RequestParamController {

	private final EmployeeMapper employeeMapper;
	
	@ModelAttribute
	public LoginForm setLoginForm() {
		return new LoginForm();
	}
	
	
	@GetMapping("/")
	public String showIndex() {
		return "index";
	}
	
	
	@PostMapping("login")
	public String showLogin(@Validated LoginForm form,
			BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			//入力チェックNG
			return "index";
		}
		String perCd = form.getPerCd();
		String birthDt= form.getPassword();
		String password = form.getPassword();
		model.addAttribute("employee",employeeMapper.getEmployeeByPerCdandBirthDtOrPassword(perCd, birthDt, password));
		
		return "menu";
	}
	
}
