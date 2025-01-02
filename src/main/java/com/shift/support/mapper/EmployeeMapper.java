package com.shift.support.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
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
	
	//役職、部門、店舗を結合した全権取得
	@Select("select r.id AS role_id , r.name AS role_name, "
			+ "d.id AS dep_id, d.name AS dep_name, "
			+ "s.store_code AS store_code, s.name AS store_name, "
			+ "first_name, last_name, work_per_week, work_per_day from m_employees e"
			+ " inner join m_roles r on e.role_id = r.id"
			+ " inner join m_departments d on e.department_id = d.id"
			+ " inner join m_stores s on e.store_code = s.store_code;")
	@Results({
		@Result(property = "role.id", column = "role_id"),
		@Result(property = "role.name", column = "role_name"),
		@Result(property = "department.id", column = "dep_id"),
		@Result(property = "department.name", column = "dep_name"),
		@Result(property = "store.storeCode", column = "store_code"),
		@Result(property = "store.name", column = "store_name"),
		@Result(property = "firstName" , column = "first_name"),
		@Result(property = "lastName" , column = "last_name"),
		@Result(property = "workPerWeek" , column = "work_per_week"),
		@Result(property = "workPerDay" , column = "work_per_day"),
	})
	List<Employee> getAllWithRoleAndDepartmentAndStore();
	
	
	//登録
	@Insert
	("INSERT INTO m_employees values(#{employeeCode},#{roleId},#{storeCode},#{departmentId},#{firstName},#{lastName},#{dateOfBirth},#{password},#{workPerWeek},#{workPerDay},null)")
	void create(String employeeCode, int roleId, String storeCode, int departmentId, String firstName, String lastName, String dateOfBirth, String password, int workPerWeek, int workPerDay);
}
