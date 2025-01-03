package com.shift.support.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.shift.support.entity.Dayoff;

@Mapper
public interface DayoffMapper {
	
	@Select("WITH store_employees AS ("
			+ "SELECT m_employees.employee_code FROM m_employees"
			+ "WHERE m_employees.store_code = #{storeCode}"
			+ ")"
			+ "SELECT * FROM m_dayoffs"
			+ "WHERE m_dayoffs.date BETWEEN #{start} AND #{end}"
			+ "AND m_dayoffs.employee_code IN store_employees")
	List<Dayoff> getByStoreCodeAndDateBetween(
			@Param("storeCode") String storeCode,
			@Param("start") String start,
			@Param("end") String end);
	
}
