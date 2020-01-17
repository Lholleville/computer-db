package com.excilys.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.excilys.dao.ComputerDAO;
import com.excilys.model.Computer;

@Service
public class ComputerService {

	private ComputerDAO computerDAO;
	
	public ComputerService(ComputerDAO computerDAO) {
		this.computerDAO = computerDAO;
	}
	
	public  List<Computer> findAllComputers() {
		return computerDAO.list();
		}
	
	public  List<Computer> findAllComputers(int limit, int offset) {
		return computerDAO.list(limit, offset);	
		}

	public  Computer findOneComputer(long id) {	
		return computerDAO.find(id);
	}
	
	public  void deleteOneComputer(Computer c) {
		computerDAO.delete(c);
	}
	
	public void createOneComputer(Computer c) {		
		computerDAO.create(c);
	}

	public void updateOneComputer(Computer c) {
		computerDAO.update(c);
	}
	
	public int countComputer() {
		return computerDAO.getComputerNumber();
	}
	
	public List<Computer> searchComputerByName(int limite, int offset, String name) {
		return computerDAO.findByName(name, limite, offset);
	}
}
