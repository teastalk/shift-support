package com.shift.support.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.shift.support.entity.Employee;

@Repository
public class EmployeeRepository {
	private final JdbcTemplate jdbcTemplate;

	public EmployeeRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<Employee> findAllEmployees() {
		String sql = "SELECT * FROM m_employees";
		return jdbcTemplate.query(sql, new EmployeeRowMapper());
	}

	public List<Employee> findAllEmployeesWithShifts() {
		String sql = "SELECT e.*, s.shift_date, s.shift_start, s.shift_end FROM m_employees e LEFT JOIN t_shifts s ON e.employee_code = s.employee_code";
		return jdbcTemplate.query(sql, new EmployeeRowMapper());
	}

	private static class EmployeeRowMapper implements RowMapper<Employee> {
		@Override
		public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
			Employee employee = new Employee();
			employee.setEmployeeCode(rs.getString("employee_code"));
			employee.setFirstName(rs.getString("first_name"));
			employee.setLastName(rs.getString("last_name"));
			employee.setDateOfBirth(rs.getDate("date_of_birth").toString());
			return employee;
		}
	}
}
