package br.com.medicalclinic.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.medicalclinic.domain.Specialty;
import br.com.medicalclinic.repository.SpecialtyRepository;
import br.com.medicalclinic.services.exceptions.SpecialtyNotFoundException;

@Service
public class SpecialtyService {

	@Autowired
	private SpecialtyRepository specialtyRepository;

	public List<Specialty> list() {
		return specialtyRepository.findAll();
	}

	public Specialty search(Long id) {
		Specialty specialty = specialtyRepository.findOne(id);
		if (specialty == null) {
			throw new SpecialtyNotFoundException("A especialidade médica não foi localizada.");
		}
		return specialty;
	}

	public Specialty save(Specialty specialty) {
		specialty.setId(null);
		return specialtyRepository.save(specialty);
	}

	public void delete(Long id) {
		try {
			specialtyRepository.delete(id);
		} catch (EmptyResultDataAccessException e) {
			throw new SpecialtyNotFoundException("A especialidade médica não foi localizada para remoção.");
		}
	}

	public void update(Specialty specialty) {
		checkExistence(specialty);
		specialtyRepository.save(specialty);
	}

	private void checkExistence(Specialty specialty) {
		search(specialty.getId());
	}

}
