package main.java.app.validator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import main.java.app.service.ComputerService;
import main.java.app.validator.exceptions.CompanyDoesNotExistException;
import main.java.app.validator.exceptions.DiscontinuedException;
import main.java.app.validator.exceptions.NameContentException;
import main.java.app.validator.exceptions.NameLengthException;
import main.java.app.validator.modeltovalidate.ComputerValidate;

public class Validator {

	private static Validator instance = Validator.getInstance();

	private Validator() {
	}

	public static Validator getInstance() {
		if (instance == null) {
			instance = new Validator();
		}
		return instance;
	}

	public static boolean validateComputer(ComputerValidate c) {

		try {
			Rules.legalName(c.getName());
		} catch (NameLengthException | NameContentException e) {
			return false;
		}

		try {
			if (c.getIntroduced() != null && c.getDiscontinued() != null) {
				Rules.isAbeforeB(c.getIntroduced(), c.getDiscontinued());
			}
		} catch (DiscontinuedException e) {
			return false;
		}

		try {
			Rules.legalCompany(c.getCompanyId());
		} catch (CompanyDoesNotExistException e) {
			return false;
		}
		return true;
	}
}
