package com.shift.support.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.shift.support.entity.Dayoff;
import com.shift.support.entity.Employee;
import com.shift.support.entity.Shift;
import com.shift.support.entity.Store;
import com.shift.support.mapper.DayoffMapper;
import com.shift.support.mapper.EmployeeMapper;
import com.shift.support.mapper.ShiftMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShiftService {

	private EmployeeMapper employeeMapper;

	private DayoffMapper dayoffMapper;

	private ShiftMapper shiftMapper;

	public void generate(LocalDate date, Store store) {
		date = date.withDayOfMonth(19);
		LocalDate end = date.plusMonths(1).withDayOfMonth(18);
		List<Employee> employees = employeeMapper.getByStoreCode(store.getStoreCode());
		List<Dayoff> dayoffs = dayoffMapper.getByStoreCodeAndDateBetween(store.getStoreCode(), date.toString(),
				end.toString());
		Map<String, Set<LocalDate>> employeeDayoffs = new HashMap<>();
		List<Shift> shifts = new ArrayList<>();
		
		for (Employee employee : employees) {
			employeeDayoffs.put(employee.getEmployeeCode(), new HashSet<>());
		}
		
		for (Dayoff dayoff : dayoffs) {
			employeeDayoffs.get(dayoff.getEmployeeCode()).add(dayoff.getDate());
		}
		
		while (!date.isAfter(end)) {
			int period = (int) Math.min(ChronoUnit.DAYS.between(date, end), 7);
			LocalDate periodStart = date;
			LocalDate periodEnd = date.plusDays(period - 1);
			int[] employeeCount = new int[period];
			for (Employee employee : employees) {
				List<LocalDate> created = new ArrayList<>();
				int remain = employee.getWorkPerWeek();
				boolean changed = true;
				while (remain > 0 && changed) {
					changed = false;
					List<LocalDate> dateOptions = new ArrayList<>();
					int minEmployeeCount = Integer.MAX_VALUE;
					LocalDate target = date;
					while (!target.isAfter(periodEnd)) {
						int index = (int) ChronoUnit.DAYS.between(periodStart, target);
						if (employeeDayoffs.get(employee.getEmployeeCode()).contains(target)
								|| created.contains(target)) {
							continue;
						}
						if (employeeCount[index] < minEmployeeCount) {
							dateOptions = new ArrayList<>();
						}
						if (employeeCount[index] <= minEmployeeCount) {
							dateOptions.add(target);
						}
					}
					if (dateOptions.size() > 0) {
						int amount = Math.min(dateOptions.size(), remain);
						Collections.shuffle(dateOptions);
						for (int i = 0; i < amount; i++) {
							target = dateOptions.get(i);
							int index = (int) ChronoUnit.DAYS.between(periodStart, target);
							Shift shift = new Shift(0, employee.getEmployeeCode(), employee.getDepartmentId(), target,
									LocalTime.of(9, 0), LocalTime.of(18, 0));
							created.add(target);
							shifts.add(shift);
							employeeCount[index]++;
							remain--;
						}
						changed = true;
					}
				}
			}
			date = date.plusDays(period);
		}
		for (Shift shift : shifts) {
			shiftMapper.create(shift.getEmployeeCode(), shift.getDepartmentId(), shift.getShiftDate().toString(),
					shift.getShiftStart().toString(), shift.getShiftEnd().toString());
		}
	}
}
