package br.com.arthdroid1.exceptions;

public class NotFindBillException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public NotFindBillException() {
		super("Bill not found");
	}

}
