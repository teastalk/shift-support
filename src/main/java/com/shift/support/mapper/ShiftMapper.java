package com.shift.support.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface ShiftMapper {
	
	//登録
	@Insert("INSERT INTO t_shifts"
			+ "(employee_code, department_id, date, start, end)"
			+ "VALUES"
			+ "(#{employeeCode}, #{departmentId}, #{date}, #{start}, #{end})")
	void create(
			@Param("employeeCode") String employeeCode,
			@Param("departmentId") int departmentId,
			@Param("date") String date,
			@Param("start") String start,
			@Param("end") String end);
}
