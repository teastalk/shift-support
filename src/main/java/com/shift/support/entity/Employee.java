package com.shift.support.entity;

import java.util.Date;

import lombok.Data;

@Data
public class Employee {
	private String employeeCode;
	private int roleId;
	private String storeCode;
	private int departmentId;
	private String firstName;
	private String lastName;
	private String dateOfBirth;
	private String password;
	private int workPerWeek;
	private int workPerDay;
	private Date deleteAt; 
}
