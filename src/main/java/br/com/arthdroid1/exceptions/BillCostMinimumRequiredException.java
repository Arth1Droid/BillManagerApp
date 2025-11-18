package br.com.arthdroid1.exceptions;

public class BillCostMinimumRequiredException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public BillCostMinimumRequiredException() {
		super("The bill cost must be greater than 0");
	}

}
