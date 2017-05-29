package br.com.medicalclinic.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.medicalclinic.domain.DetailError;
import br.com.medicalclinic.services.exceptions.ConsultationNotFoundException;
import br.com.medicalclinic.services.exceptions.DoctorNotFoundException;
import br.com.medicalclinic.services.exceptions.PatientNotFoundException;
import br.com.medicalclinic.services.exceptions.SpecialtyNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(DoctorNotFoundException.class)
	public ResponseEntity<DetailError> handleDoctorNotFoundException(DoctorNotFoundException e, HttpServletRequest request) {
		DetailError error = new DetailError();
		error.setStatus(404l);
		error.setTitle(e.getMessage());
		error.setDeveloperMessage("http://erros.medicalclinic.com.br/404");
		error.setTimeStamp(System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	@ExceptionHandler(SpecialtyNotFoundException.class)
	public ResponseEntity<DetailError> handleSpecialtyNotFoundException(SpecialtyNotFoundException e, HttpServletRequest request) {
		DetailError error = new DetailError();
		error.setStatus(404l);
		error.setTitle(e.getMessage());
		error.setDeveloperMessage("http://erros.medicalclinic.com.br/404");
		error.setTimeStamp(System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	@ExceptionHandler(PatientNotFoundException.class)
	public ResponseEntity<DetailError> handlePatientNotFoundException(PatientNotFoundException e, HttpServletRequest request) {
		DetailError error = new DetailError();
		error.setStatus(404l);
		error.setTitle(e.getMessage());
		error.setDeveloperMessage("http://erros.medicalclinic.com.br/404");
		error.setTimeStamp(System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	@ExceptionHandler(ConsultationNotFoundException.class)
	public ResponseEntity<DetailError> handleConsultationNotFoundException(ConsultationNotFoundException e, HttpServletRequest request) {
		DetailError error = new DetailError();
		error.setStatus(404l);
		error.setTitle(e.getMessage());
		error.setDeveloperMessage("http://erros.medicalclinic.com.br/404");
		error.setTimeStamp(System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<DetailError> handleDataIntegrityViolationException(DataIntegrityViolationException e, HttpServletRequest request) {
		DetailError error = new DetailError();
		error.setStatus(400l);
		error.setTitle("Requisição inválida");
		error.setDeveloperMessage("http://erros.medicalclinic.com.br/400");
		error.setTimeStamp(System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

}
