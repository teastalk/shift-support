package com.shift.support.entity;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Dayoff {
	
	private int id;
	private String employeeCode;
	private int typeId;
	private LocalDate date;
	
}
