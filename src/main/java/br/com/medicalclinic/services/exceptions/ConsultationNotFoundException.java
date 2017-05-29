package br.com.medicalclinic.services.exceptions;

public class ConsultationNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ConsultationNotFoundException(String msg) {
		super(msg);
	}

	public ConsultationNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
