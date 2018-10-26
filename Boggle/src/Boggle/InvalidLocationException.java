package Boggle;

import java.util.InputMismatchException;

public class InvalidLocationException extends InputMismatchException {

	private static final long serialVersionUID = 1L;
	public InvalidLocationException() {}
	public InvalidLocationException(String message)
	{
		super(message);
	}
}