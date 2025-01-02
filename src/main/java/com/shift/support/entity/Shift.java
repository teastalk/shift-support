package com.shift.support.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Data;

@Data
public class Shift {
	
	private int id;
	private String employeeCode;
	private int departmentId;
	private LocalDate ShiftDate;
	private LocalTime shiftStart;
	private LocalTime shiftEnd;
}
