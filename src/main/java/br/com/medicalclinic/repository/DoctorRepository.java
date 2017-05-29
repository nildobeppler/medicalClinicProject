package br.com.medicalclinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.medicalclinic.domain.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

}
