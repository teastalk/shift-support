package com.shift.support.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.shift.support.entity.Department;

@Mapper
public interface DepartmentMapper {
//	全権取得
	@Select("SELECT * FROM m_departments")
	List<Department> getAll();
	
//	IDから単体取得
	@Select("SELECT * FROM m_departments WHERE id = #{id}")
	Department getById(int id);
}
