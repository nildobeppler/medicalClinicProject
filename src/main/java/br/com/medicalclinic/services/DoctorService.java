package br.com.medicalclinic.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.medicalclinic.domain.Doctor;
import br.com.medicalclinic.repository.DoctorRepository;
import br.com.medicalclinic.services.exceptions.DoctorNotFoundException;

@Service
public class DoctorService {

	@Autowired
	private DoctorRepository doctorRepository;

	public List<Doctor> list() {
		return doctorRepository.findAll();
	}

	public Doctor search(Long id) {
		Doctor doctor = doctorRepository.findOne(id);
		if (doctor == null) {
			throw new DoctorNotFoundException("O médico não foi encontrado.");
		}
		return doctor;
	}

	public Doctor save(Doctor doctor) {
		doctor.setId(null);
		return doctorRepository.save(doctor);
	}

	public void delete(Long id) {
		try {
			doctorRepository.delete(id);
		} catch (EmptyResultDataAccessException e) {
			throw new DoctorNotFoundException("O médico não foi encontrado.");
		}
	}

	public void update(Doctor doctor) {
		checkExistence(doctor);
		doctorRepository.save(doctor);
	}

	private void checkExistence(Doctor doctor) {
		search(doctor.getId());
	}
}
