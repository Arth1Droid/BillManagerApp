package br.com.arthdroid1.exceptions;

public class ExistingBillException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public ExistingBillException() {
		super("The bill already exist");
	}
	

}
