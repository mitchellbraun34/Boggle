package Boggle;

import java.util.InputMismatchException;

public class InvalidWordException extends InputMismatchException {

	private static final long serialVersionUID = 1L;
	public InvalidWordException() {}
	public InvalidWordException(String message)
	{
		super(message);
	}
}