package main.java.app.validator.exceptions;

public class CompanyDoesNotExistException extends Exception{
	public CompanyDoesNotExistException() {
		System.out.println("The company does not exist.");
	}
}
