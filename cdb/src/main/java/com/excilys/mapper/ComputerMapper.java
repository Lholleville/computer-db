package com.excilys.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.excilys.dto.*;
import com.excilys.model.*;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class ComputerMapper implements RowMapper<Computer>{

	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	@Override
	public Computer mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		return new Computer.ComputerBuilder()
				.buildComputerId(rs.getLong("id"))
				.buildComputerName(rs.getString("name"))
				.buildComputerIntroducedDate(rs.getTimestamp("introduced").toLocalDateTime().toLocalDate())
				.buildComputerDiscontinuedDate(rs.getTimestamp("discontinued").toLocalDateTime().toLocalDate())
				.buildComputerCompany(
						new Company.CompanyBuilder()
							.buildCompanyId(rs.getLong("company_id"))
							.buildCompanyName(rs.getString("company_name"))
							.build()
				)
				.build();
				
	}
	
	public Computer computerDTOToComputer(ComputerDTO computerDTO) {
		String dateStringInt = computerDTO.getIntroducedDate();
		LocalDate introduced = dateStringInt.equals("") ? null : LocalDate.parse(dateStringInt, formatter);
		String dateStringDis = computerDTO.getDiscontinuedDate();
		LocalDate discontinued = dateStringInt.equals("") ? null : LocalDate.parse(dateStringDis, formatter);
		CompanyDTO companyDTO = computerDTO.getCompanyDTO();
		
		return new Computer.ComputerBuilder()
				.buildComputerId(computerDTO.getIdComputer())
				.buildComputerName(computerDTO.getName())
				.buildComputerIntroducedDate(introduced)
				.buildComputerDiscontinuedDate(discontinued)
				.buildComputerCompany(new Company.CompanyBuilder()
						.buildCompanyId(companyDTO.getIdCompany())
						.buildCompanyName(companyDTO.getNameCompany())
						.build())
				.build();
	}
	
	public ComputerDTO ComputerToComputerDTO(Computer computer) {
		
		return new ComputerDTO.ComputerDTOBuilder()
				.buildComputerDTOId(computer.getId())
				.buildComputertDTOName(computer.getName())
				.buildComputerDTOIntroducedDate(computer.getIntroducedDate().toString())
				.buildComputerDTODiscontinuedDate(computer.getDiscontinuedDate().toString())
				.buildComputerDTOCompany(new CompanyDTO.CompanyDTOBuilder()
						.buildCompanyDTOId(computer.getCompany().getId())
						.buildCompanyDTOName(computer.getCompany().getName())
						.build())
				.build();
	}
}
