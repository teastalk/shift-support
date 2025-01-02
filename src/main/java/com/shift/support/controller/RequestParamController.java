package com.shift.support.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.shift.support.entity.Employee;
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
	@PostMapping("menu")
	public String showMenu(Model model, HttpSession session) {
		model.addAttribute("employee", session.getAttribute("employee"));
		// ログイン失敗
		if(model.getAttribute("employee") == null) {
			return "index";
		}
		
		return "menu";
	}
	
	
	@PostMapping("login")
	public String showLogin(@Validated LoginForm form,
			BindingResult bindingResult, Model model, HttpSession session) {
		if (bindingResult.hasErrors()) {
			//入力チェックNG
			return "index";
		}
		String perCd = form.getPerCd();
		String birthDt= form.getPassword();
		String password = form.getPassword();
		
		// ログインユーザーを取得
		Employee loginUser = employeeMapper.getEmployeeByPerCdandBirthDtOrPassword(perCd, birthDt, password);
		
		// ログイン失敗
		if(loginUser == null) {
			return "index";
		}
		
		
		model.addAttribute("employee",loginUser);
		session.setAttribute("employee", loginUser);
		return "menu";
	}
	
	
	
}
