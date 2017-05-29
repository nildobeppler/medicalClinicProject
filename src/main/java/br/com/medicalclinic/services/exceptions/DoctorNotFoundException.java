package br.com.medicalclinic.services.exceptions;

public class DoctorNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DoctorNotFoundException(String msg) {
		super(msg);
	}

	public DoctorNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
