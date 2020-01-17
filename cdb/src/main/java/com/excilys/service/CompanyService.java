package com.excilys.service;

import java.util.ArrayList;
import org.springframework.stereotype.Service;
import com.excilys.dao.*;
import com.excilys.model.Company;

@Service
public class CompanyService {
    
	private CompanyDAO companyDao;
	
	public CompanyService(CompanyDAO companyDao) {
		this.companyDao = companyDao;
	}
	
	public void displayAllCompanies() {
        for(Company company : companyDao.list()) {
            System.out.println(company.toString());
        }
	}
	
	public ArrayList<Company> findAllCompanies(){
		return companyDao.list();
	}
}
