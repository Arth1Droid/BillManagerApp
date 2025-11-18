package br.com.arthdroid1.exceptions;

public class NullBillException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public NullBillException() {
		super("The bill  cannot be null ");
	}

}
