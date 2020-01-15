package main.java.app.dao;


import java.util.ArrayList;
import org.springframework.jdbc.core.JdbcTemplate;
import main.java.app.model.Company;
import main.java.app.model.CompanyMapper;


public class CompanyDAO{

	private JdbcTemplate jdbcTemplate;
	
	/*SQL QUERIES*/
	private final String SQL_FIND_ALL_COMPANIES = "SELECT id, name FROM company";
	private final String SQL_FIND_COMPANY = "SELECT id, name FROM company WHERE id = ?";
	
	public CompanyDAO() {}
	
	public ArrayList<Company> list(){
		return new ArrayList<Company>(jdbcTemplate.query(SQL_FIND_ALL_COMPANIES, new CompanyMapper()));
	}

	public Company find(long id) {
		return jdbcTemplate.queryForObject(SQL_FIND_COMPANY, new Object[] {id}, new CompanyMapper());
	}
}
