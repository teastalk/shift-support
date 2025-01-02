package com.shift.support.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.shift.support.entity.Employee;
import com.shift.support.entity.Shift;
import com.shift.support.repository.EmployeeRepository;
import com.shift.support.repository.ShiftRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ShiftController {

	private final ShiftRepository shiftRepository;
	private final EmployeeRepository employeeRepository;
	
	@PostMapping("shift/view")
	public String showShiftView(Model model, HttpSession session) {
		List<Shift> shifts = shiftRepository.findAllShifts();
		List<String> days = getDaysOfMonth();
		List<Employee> employees = employeeRepository.findAllEmployeesWithShifts();
		model.addAttribute("days", days);
		model.addAttribute("shifts",shifts);
		model.addAttribute("employees", employees);
		model.addAttribute("employee", session.getAttribute("employee"));

		return "shift/shift_view";
	}

	private List<String> getDaysOfMonth() {
		LocalDate start = LocalDate.now().withDayOfMonth(19);
		LocalDate end = start.plusMonths(1).withDayOfMonth(18);
		return start.datesUntil(end.plusDays(1)).map(date -> date.format(DateTimeFormatter.ofPattern("MM/dd")))
				.collect(Collectors.toList());
	}
}
