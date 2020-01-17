package com.excilys.model;

import java.time.*;

public class Computer {

	private long id;
	
	private String name;
	
	private LocalDate introductionDate;
	
	private LocalDate discontinuedDate;
	
	private Company company;
	
	private Computer(ComputerBuilder builder) {
		this.id = builder.getBuildComputerId();
		this.name = builder.getBuildComputerName();
		this.introductionDate = builder.getBuilderComputerIntroducedDate();
		this.discontinuedDate = builder.getBuilderComputerDiscontinuedDate();
		this.company = builder.getBuilderComputerCompany();
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

	public LocalDate getIntroductionDate() {
		return introductionDate;
	}

	public LocalDate getIntroducedDate() {
		return introductionDate;
	}
	public void setIntroducedDate(LocalDate introductionDate) {
		this.introductionDate = introductionDate;
	}
	
	public void setIntroductionDate(LocalDate introductionDate) {
		this.introductionDate = introductionDate;
	}

	public LocalDate getDiscontinuedDate() {
		return discontinuedDate;
	}

	public void setDiscontinuedDate(LocalDate discontinuedDate) {
		this.discontinuedDate = discontinuedDate;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}	
	
	public String toString() {
		return this.getId() 
				+ " " + this.getName() 
				+ " " + this.getIntroductionDate() 
				+ " " +  this.getDiscontinuedDate() 
				+ " " +  this.getCompany().getName();
	}
	
public static class ComputerBuilder {
		
		private long builderComputerId;
		private String builderComputerName;
		private LocalDate builderComputerIntroducedDate;
		private LocalDate builderComputerDiscontinuedDate;
		private Company builderComputerCompany;
		
		public ComputerBuilder() {};
		
    	public ComputerBuilder buildComputerId(long id) {
    		this.builderComputerId = id;
    		return this;
    	}
    	public ComputerBuilder buildComputerName(String name) {
    		this.builderComputerName = name;
    		return this;
    	}
    	public ComputerBuilder buildComputerIntroducedDate(LocalDate introducedDate) {
    		this.builderComputerIntroducedDate = introducedDate;
    		return this;
    	}
    	public ComputerBuilder buildComputerDiscontinuedDate(LocalDate discontinuedDate) {
    		this.builderComputerDiscontinuedDate = discontinuedDate;
    		return this;
    	}
    	public ComputerBuilder buildComputerCompany(Company company) {
    		this.builderComputerCompany = company;
    		return this;
    	}
    	
    	public long getBuildComputerId() {
    		return this.builderComputerId;
    	}
    	
    	public String getBuildComputerName() {
    		return this.builderComputerName;
    	}
    	
    	public LocalDate getBuilderComputerIntroducedDate() {
    		return this.builderComputerIntroducedDate;
    	}
    	
    	public LocalDate getBuilderComputerDiscontinuedDate() {
    		return this.builderComputerDiscontinuedDate;
    	}
    	
    	public Company getBuilderComputerCompany() {
    		return this.builderComputerCompany;
    	}
    	
    	public Computer build() {
    		return new Computer(this);
    	}
	}
}
