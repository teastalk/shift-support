package com.shift.support.service;

import com.shift.support.entity.Role;
import com.shift.support.mapper.RoleMapper;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class RoleService {
	private final RoleMapper roleMapper;
	
	//役職の存在チェック
	public boolean isExist(int roleId) throws Exception {
		
		Role role = roleMapper.getById(roleId); 	
		if(role == null) {
			throw new Exception();
		}
		return true;
	};
}
