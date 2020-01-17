package com.excilys.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import com.excilys.model.Company;
import org.springframework.jdbc.core.RowMapper;

public class CompanyMapper implements RowMapper<Company>{

	@Override
	public Company mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new Company.CompanyBuilder()
				.buildCompanyId(rs.getLong("id"))
				.buildCompanyName(rs.getString("name"))
				.build();
	}

}
