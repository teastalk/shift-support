package com.shift.support.entity;

import lombok.Data;

@Data
public class Employee {
	private String perCd;
	private int roleId;
	private String shopCd;
	private int departmentId;
	private String fname;
	private String lname;
	private String birthDt;
	private String password;
	private int workPerWeek;
	private int workHours;
	private String restFlg;
	private String delFlg; 
}
