package com.excilys.model;

import java.util.ArrayList;

public class Company {
	
	private long id;
	
	private String name;
	
	private ArrayList<Computer> listComputer = new ArrayList<Computer>();
	
	private Company(CompanyBuilder builder) {
		this.id = builder.getBuildCompanyId();
		this.name = builder.getBuildCompanyName();
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Computer> getListComputer() {
		return listComputer;
	}

	public void setListComputer(ArrayList<Computer> listComputer) {
		this.listComputer = listComputer;
	}
	
	public void addComputer(Computer computer) {
		this.listComputer.add(computer);
	}
	
	public Computer getComputer(int i) {
		return this.listComputer.get(i);
	}
	
	@Override
	public String toString() {
		return this.getId() + " " + this.getName(); 
	}
	
public static class CompanyBuilder {
    	
    	private long buildCompanyId;
    	private String buildCompanyName;
    	
    	public CompanyBuilder() {};
    	
    	public CompanyBuilder buildCompanyId(long idCompany) {
    		this.buildCompanyId = idCompany;
    		return this;
    	}
    	public CompanyBuilder buildCompanyName(String nameCompany) {
    		this.buildCompanyName = nameCompany;
    		return this;
    	}
    	
    	public Company build() {
    		return new Company(this);
    	}
    	
    	public long getBuildCompanyId() {
    		return this.buildCompanyId;
    	}
    	
    	public String getBuildCompanyName() {
    		return this.buildCompanyName;
    	}
    }

}
