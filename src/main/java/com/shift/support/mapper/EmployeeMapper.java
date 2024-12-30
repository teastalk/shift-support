package com.shift.support.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.shift.support.entity.Employee;

@Mapper
public interface EmployeeMapper {

	@Select("SELECT * FROM m_employees WHERE employee_code = #{employeeCode} AND (date_of_birth = #{dateOfBirth} OR password = #{password})")
	Employee getEmployeeByPerCdandBirthDtOrPassword(String employeeCode, String dateOfBirth, String password);
}
