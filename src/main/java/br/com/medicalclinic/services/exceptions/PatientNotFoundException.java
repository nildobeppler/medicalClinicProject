package br.com.medicalclinic.services.exceptions;

public class PatientNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PatientNotFoundException(String msg) {
		super(msg);
	}

	public PatientNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
