package main.java.app.validator.exceptions;

public class NameContentException extends Exception{
	public NameContentException() {
		System.out.println("The name must contain only latin alphanumeric characters");
	}
}
