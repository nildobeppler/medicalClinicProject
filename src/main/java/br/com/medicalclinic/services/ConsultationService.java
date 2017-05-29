package br.com.medicalclinic.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.medicalclinic.domain.Consultation;
import br.com.medicalclinic.repository.ConsultationRepository;
import br.com.medicalclinic.services.exceptions.ConsultationNotFoundException;

@Service
public class ConsultationService {

	@Autowired
	private ConsultationRepository consultationRepository;

	public List<Consultation> list() {
		return consultationRepository.findAll();
	}

	public Consultation search(Long id) {
		Consultation consultation = consultationRepository.findOne(id);
		if (consultation == null) {
			throw new ConsultationNotFoundException("A consulta médica não foi encontrada.");
		}
		return consultation;
	}

	public Consultation save(Consultation consultation) {
		consultation.setId(null);
		return consultationRepository.save(consultation);
	}

	public void delete(Long id) {
		try {
			consultationRepository.delete(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ConsultationNotFoundException("A consulta médica não foi encontrada para remoção.");
		}
	}

	public void update(Consultation consultation) {
		checkExistence(consultation);
		consultationRepository.save(consultation);
	}

	private void checkExistence(Consultation consultation) {
		search(consultation.getId());
	}
}
