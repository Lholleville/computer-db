package main.java.app.validator;

import java.time.LocalDate;
import java.util.Optional;
import java.util.regex.*;  
import main.java.app.dao.CompanyDAO;
import main.java.app.model.Company;
import main.java.app.validator.exceptions.*;

public  class Rules {
	public static void legalName(String str) throws NameLengthException, NameContentException {
		if(str.length() < 2 || str.length() > 255) {
			throw new NameLengthException();
		}
		if(!Pattern.matches("^[0-9a-zA-Z \\-\\.\\'\\_]*$", str)) {
			throw new NameContentException();
		}
	}
	
	public static void isAbeforeB(LocalDate a, LocalDate b) throws DiscontinuedException {
		if(!a.isBefore(b)) {
			throw new DiscontinuedException();
		}
	}
	
	public static void legalCompany(long id) throws CompanyDoesNotExistException {
		Optional<Company> optional = CompanyDAO.getInstance().find(id);
		if(!optional.isPresent() || id == 0) {
			throw new CompanyDoesNotExistException();
		}
	}
}
