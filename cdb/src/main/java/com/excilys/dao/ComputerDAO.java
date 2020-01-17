package com.excilys.dao;


import java.util.ArrayList;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.excilys.model.Computer;
import com.excilys.mapper.ComputerMapper;

/**
 * @author holle
 */

@Repository
public class ComputerDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	/*SQL QUERIES*/
	private final String SQL_FIND_COMPUTER = "SELECT c.id, c.name, c.introduced, c.discontinued, c.company_id, company.id, company.name as company_name"
    		+ " FROM computer c"
    		+ " LEFT JOIN company ON c.company_id = company.id"
	    	+ " WHERE c.id = ?";
	private final String SQL_FIND_ALL_COMPUTER = "SELECT c.id, c.name, c.introduced, c.discontinued, c.company_id, company.id, company.name as company_name"
    		+ " FROM computer c"
    		+ " LEFT JOIN company ON c.company_id = company.id";
	private final String SQL_CREATE_COMPUTER = "INSERT INTO computer (name, introduced, discontinued, company_id) VALUES(?, ?, ?, ?)";
	private final String SQL_DELETE_COMPUTER = "DELETE FROM computer WHERE computer.id = ?";
	private final String SQL_UPDATE_COMPUTER = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ?";
	private final String SQL_FIND_LAST_COMPUTER_ID = "SELECT id, name, introduced, discontinued, company_id FROM computer ORDER BY id DESC LIMIT 1";
	private final String SQL_GET_COMPUTER_COUNT = "SELECT count(id) as nb FROM computer";
	private final String PAGINATION = " LIMIT ?, ?";
	private final String SQL_FIND_COMPUTER_BY_NAME = "SELECT c.id, c.name, c.introduced, c.discontinued, c.company_id, company.id, company.name as company_name"
    		+ " FROM computer c"
    		+ " LEFT JOIN company ON c.company_id = company.id"
    		+ " WHERE c.name LIKE ?;";
	
	public ComputerDAO(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public Computer find(long id) {	
		return jdbcTemplate.queryForObject(SQL_FIND_COMPUTER, new Object[] { id }, new ComputerMapper());
	}

	public ArrayList<Computer> list() {
		return new ArrayList<Computer>(jdbcTemplate.query(SQL_FIND_ALL_COMPUTER, new ComputerMapper()));
	}
	
	public ArrayList<Computer> list(int page, int show){
		int offset = page * show - (show - 1) - 1;
		return new ArrayList<Computer>(jdbcTemplate.query(SQL_FIND_ALL_COMPUTER + PAGINATION, new Object[] {offset, show}, new ComputerMapper()));
	}

	public boolean create(Computer computer) {
		return jdbcTemplate.update(SQL_CREATE_COMPUTER, computer.getId(), computer.getIntroducedDate(), computer.getDiscontinuedDate(), computer.getCompany().getId()) > 0;	
	}	

	public boolean update(Computer computer) {
		return jdbcTemplate.update(SQL_UPDATE_COMPUTER, computer.getId(), computer.getIntroducedDate(), computer.getDiscontinuedDate(), computer.getCompany().getId()) > 0;	
	}

	public boolean delete(Computer computer) {
		return jdbcTemplate.update(SQL_DELETE_COMPUTER, computer.getId()) > 0;
	}

	public int getComputerNumber() {
		return jdbcTemplate.queryForObject(SQL_GET_COMPUTER_COUNT, Integer.class);
	}

	public ArrayList<Computer> findByName(String name){
		return new ArrayList<Computer>(jdbcTemplate.query(SQL_FIND_COMPUTER_BY_NAME, new Object[] { "%"+name+"%" }, new ComputerMapper()));
	}
	
	public ArrayList<Computer> findByName(String name, int limit, int offset){
		return new ArrayList<Computer>(jdbcTemplate.query(SQL_FIND_COMPUTER_BY_NAME, new Object[] { "%"+name+"%" }, new ComputerMapper()));
	}
}
