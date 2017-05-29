package br.com.medicalclinic.services.exceptions;

public class SpecialtyNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SpecialtyNotFoundException(String msg) {
		super(msg);
	}

	public SpecialtyNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
