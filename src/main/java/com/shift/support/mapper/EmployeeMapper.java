package com.shift.support.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.shift.support.entity.Employee;

@Mapper
public interface EmployeeMapper {

	@Select("SELECT * FROM m_employees WHERE employee_code = #{employeeCode} AND (date_of_birth = #{dateOfBirth} OR password = #{password})")
	@Results({
		@Result(property = "employeeCode", column = "employee_code"),
		@Result(property = "roleId" , column = "role_id"),
		@Result(property = "storeCode" , column = "store_code"),
		@Result(property = "departmentId" , column = "department_id"),
		@Result(property = "firstName" , column = "first_name"),
		@Result(property = "lastName" , column = "last_name"),
		@Result(property = "dateOfBirth" , column = "date_of_birth"),
		@Result(property = "password" , column = "password"),
		@Result(property = "workPerWeek" , column = "work_per_week"),
		@Result(property = "workPerDay" , column = "work_per_day"),
		@Result(property = "deleteAt" , column = "delete_at")
	})
	Employee getEmployeeByPerCdandBirthDtOrPassword(String employeeCode, String dateOfBirth, String password);
}
