package br.com.medicalclinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.medicalclinic.domain.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {

}
