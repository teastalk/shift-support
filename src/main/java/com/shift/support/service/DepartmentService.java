package com.shift.support.service;

import com.shift.support.entity.Department;
import com.shift.support.mapper.DepartmentMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DepartmentService {
	private final DepartmentMapper departmentMapper;
	
	//部門の存在チェック
	public boolean isExist(int departmentId) throws Exception{
		
		Department department = departmentMapper.getById(departmentId);
		
		if(department == null) {
			throw new Exception();
		}
		
		return true;
	}
	
}
