package main.java.app.validator.modeltovalidate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ComputerValidate {
	
	private String name;
	private LocalDate introduced;
	private LocalDate discontinued;
	private long companyId;
	private static DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public ComputerValidate(String name, String introduced, String discontinued, String companyId){
		this.name = name;
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
		if(!introduced.isEmpty()) {
			localdate = LocalDate.parse(introduced, f); 
		}
		this.introduced = localdate;
	}

	public LocalDate getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(String discontinued) {
		LocalDate localdate = null;
		if(!discontinued.isEmpty()) {
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
}
