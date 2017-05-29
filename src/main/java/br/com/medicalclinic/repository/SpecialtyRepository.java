package br.com.medicalclinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.medicalclinic.domain.Specialty;

public interface SpecialtyRepository extends JpaRepository<Specialty, Long> {

}
