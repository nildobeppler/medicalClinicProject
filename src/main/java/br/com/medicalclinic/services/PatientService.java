package br.com.medicalclinic.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.medicalclinic.domain.Patient;
import br.com.medicalclinic.repository.PatientRepository;
import br.com.medicalclinic.services.exceptions.PatientNotFoundException;

@Service
public class PatientService {

	@Autowired
	private PatientRepository patientRepository;

	public List<Patient> list() {
		return patientRepository.findAll();
	}

	public Patient search(Long id) {
		Patient patient = patientRepository.findOne(id);
		if (patient == null) {
			throw new PatientNotFoundException("O paciente não foi encontrado.");
		}
		return patient;
	}

	public Patient save(Patient patient) {
		patient.setId(null);
		return patientRepository.save(patient);
	}

	public void delete(Long id) {
		try {
			patientRepository.delete(id);
		} catch (EmptyResultDataAccessException e) {
			throw new PatientNotFoundException("O paciente não foi encontrado para remoção.");
		}
	}

	public void update(Patient patient) {
		checkExistence(patient);
		patientRepository.save(patient);
	}

	private void checkExistence(Patient patient) {
		search(patient.getId());
	}

}
