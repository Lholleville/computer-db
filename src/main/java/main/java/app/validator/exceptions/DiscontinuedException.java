package main.java.app.validator.exceptions;

public class DiscontinuedException extends Exception {
	public DiscontinuedException() {
		System.out.println("The discontinued date must be after the introduced date");
	}
}
