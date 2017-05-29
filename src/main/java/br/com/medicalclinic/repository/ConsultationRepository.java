package br.com.medicalclinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.medicalclinic.domain.Consultation;

public interface ConsultationRepository extends JpaRepository<Consultation, Long> {

}
