package com.shift.support.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.shift.support.entity.Shift;

@Repository
public class ShiftRepository {
	private final JdbcTemplate jdbcTemplate;

	public ShiftRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<Shift> findAllShifts() {
		String sql = "SELECT * FROM t_shifts";
		return jdbcTemplate.query(sql, new ShiftRowMapper());
	}
	public List<Shift> findShiftsByEmployeecodeAndDate(){
		return null;
	}

	private static class ShiftRowMapper implements RowMapper<Shift> {
		@Override
		public Shift mapRow(ResultSet rs, int rowNum) throws SQLException {
			Shift shift = new Shift();
			shift.setId(rs.getInt("id"));
			shift.setEmployeeCode(rs.getString("employee_code"));
			shift.setDepartmentId(rs.getInt("department_id"));
			shift.setShiftDate(rs.getDate("shift_date").toLocalDate());
			shift.setShiftStart(rs.getTime("shift_start").toLocalTime());
			shift.setShiftEnd(rs.getTime("shift_end").toLocalTime());
			return shift;
		}
	}
}
