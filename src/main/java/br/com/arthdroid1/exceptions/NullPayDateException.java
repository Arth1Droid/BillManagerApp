package br.com.arthdroid1.exceptions;

public class NullPayDateException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public NullPayDateException() {
		super("The pay date cannot be null");
	}
}
