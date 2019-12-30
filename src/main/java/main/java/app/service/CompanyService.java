package main.java.app.service;

import java.util.ArrayList;

import main.java.app.dao.CompanyDAO;
import main.java.app.model.Company;

public class CompanyService {
	
	private static CompanyDAO companyDAO = CompanyDAO.getInstance();
	
	private CompanyService() {};
	
	private static CompanyService companyService = null;
	
	public static CompanyService getInstance() {
		if (companyService == null) {
			companyService = new CompanyService();
		}
		return companyService;
	}
	
	public static void displayAllCompanies() {
        for(Company company : companyDAO.list()) {
            System.out.println(company.toString());
        }
	}
	
	public ArrayList<Company> findAllCompanies(){
		return companyDAO.list();
	}
}
