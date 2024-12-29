package com.shift.support.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.shift.support.entity.Employee;

@Mapper
public interface EmployeeMapper {

	@Select("SELECT * FROM employees WHERE per_cd = #{perCd} AND (birth_dt = #{birthDt} OR password = #{password})")
	Employee getEmployeeByPerCdandBirthDtOrPassword(String perCd, String birthDt, String password);
}
