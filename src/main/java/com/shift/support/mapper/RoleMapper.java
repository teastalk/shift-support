package com.shift.support.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.shift.support.entity.Role;

@Mapper
public interface RoleMapper {
//	全権取得
	@Select("SELECT * FROM m_roles")
	List<Role> getAll();
	
//	IDから単体取得
	@Select("SELECT * FROM m_roles WHERE id = #{id}")
	Role getById(int id);
}
