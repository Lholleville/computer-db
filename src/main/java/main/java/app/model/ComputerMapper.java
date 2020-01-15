package main.java.app.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ComputerMapper implements RowMapper<Computer>{

	@Override
	public Computer mapRow(ResultSet rs, int rowNum) throws SQLException {
		Computer computer = new Computer();
		computer.setId(rs.getLong("id"));
		computer.setName(rs.getString("name"));
		computer.setIntroducedDate(rs.getTimestamp("introduced").toLocalDateTime().toLocalDate());
		computer.setDiscontinuedDate(rs.getTimestamp("discontinued").toLocalDateTime().toLocalDate());
		computer.setCompany(new Company(rs.getLong("company_id"), rs.getString("company_name")));
		return computer;
	}

}
