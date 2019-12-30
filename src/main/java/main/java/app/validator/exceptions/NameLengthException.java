package main.java.app.validator.exceptions;

public class NameLengthException extends Exception {
	public NameLengthException() {
		System.out.println("The name must be between 2 and 255 characters");
	}
}
