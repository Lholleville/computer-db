package main.java.app.validator.modeltovalidate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ComputerValidate {
	
	private long id;
	private String name;
	private LocalDate introduced;
	private LocalDate discontinued;
	private long companyId;
	private static DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public ComputerValidate(String name, String introduced, String discontinued, String companyId){
		setName(name);
		setIntroduced(introduced);
		setDiscontinued(discontinued);
		setCompanyId(companyId);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getIntroduced() {
		return introduced;
	}

	public void setIntroduced(String introduced) {
		LocalDate localdate = null;
		if(introduced != null) {
			localdate = LocalDate.parse(introduced, f); 
		}
		this.introduced = localdate;
	}

	public LocalDate getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(String discontinued) {
		LocalDate localdate = null;
		if(discontinued != null) {
			localdate = LocalDate.parse(discontinued, f); 
		}
		this.discontinued = localdate;
	}

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		long tmp = 0;
		if(!companyId.isEmpty()) {
			tmp = Long.parseLong(companyId);
		 }
		this.companyId = tmp;
	}
	
	public long getId() {
		return this.id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String toString() {
		return this.getId() 
				+ " " + this.getName() 
				+ " " + this.getIntroduced() 
				+ " " +  this.getDiscontinued() 
				+ " " +  this.getCompanyId(); 
	}
}
