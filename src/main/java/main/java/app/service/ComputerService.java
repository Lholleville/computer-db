package main.java.app.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import main.java.app.dao.ComputerDAO;
import main.java.app.model.Computer;

public class ComputerService {
	
	private static ComputerDAO computerDAO = ComputerDAO.getInstance();
	
	private ComputerService() {};
	
	private static ComputerService computerService;
	
	public static ComputerService getInstance() {
		if(computerService == null) {
			computerService = new ComputerService();
		}
		return computerService;
	}
	
	public static void displayAllComputers() {
		for(Computer computers : computerDAO.list()) {
			System.out.println(computers.toString());
		}
	}
	
	public static ArrayList<Computer> findAllComputers(){
		return computerDAO.list();
	}
	
	public static void findComputer(long id) {
		Optional<Computer> computer = computerDAO.find(id);
		System.out.println((computer.isPresent()) ? computer.get().toString() : "No computer found with id " + id);
	}
	
	public static void createComputer(String name, LocalDate introduced, LocalDate discontinued, long company) {
		computerDAO.create(name, introduced, discontinued, company);
	}
	
	public static void updateComputer(long id, String name, LocalDate introduced, LocalDate discontinued, long companyId) {
		Optional<Computer> computer = ComputerDAO.getInstance().find(id);
		if(computer.isPresent()) { 
			System.out.println(computer.get().toString());
			computerDAO.update(id, name, introduced, discontinued, companyId);
			System.out.println("Computer : " + computer.get().getName() + " modified");
		}else {
			System.out.println("No computer to update");
		}	
	}
	
	public static void deleteComputer(long id) {
		computerDAO.delete(id);
	}
	
	public long getNbComputers() {
		return computerDAO.getComputerNumber();
	}
	
	
}
