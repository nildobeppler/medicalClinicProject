package br.com.medicalclinic.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "doctor")
public class Doctor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	@JsonInclude(Include.NON_NULL)
	private Long id;

	@Column(nullable = false, length = 14)
	@JsonInclude(Include.NON_NULL)
	@NotEmpty(message = "O CPF do médico não pode ser vazio.")
	@NotNull(message = "O CPF do médico é de preenchimento obrigatório.")
	private String cpf;

	@Column(nullable = false, length = 15)
	@JsonInclude(Include.NON_NULL)
	@NotEmpty(message = "O CRM do médico não pode ser vazio.")
	@NotNull(message = "O CRM do médico é de preenchimento obrigatório.")
	private String crm;

	@Column(nullable = false, length = 100)
	@JsonInclude(Include.NON_NULL)
	@NotEmpty(message = "O nome do médico não pode ser vazio.")
	@NotNull(message = "O nome do médico é de preenchimento obrigatório.")
	private String name;

	@Column(length = 16)
	@JsonInclude(Include.NON_NULL)
	private String phone;

	@OneToMany(mappedBy = "doctor")
	@JsonIgnore
	private List<Consultation> consultations;

	@ManyToMany
	@JoinTable(name = "specialtydoctor", 
	joinColumns = { 
			@JoinColumn(name = "iddoctor") }, 
		inverseJoinColumns = {
			@JoinColumn(name = "idspecialty") 
			})
	@JsonInclude(Include.NON_EMPTY)
	private List<Specialty> specialties;

	public Doctor() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCrm() {
		return this.crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<Consultation> getConsultations() {
		return this.consultations;
	}

	public void setConsultations(List<Consultation> consultations) {
		this.consultations = consultations;
	}

	public Consultation addConsultation(Consultation consultation) {
		getConsultations().add(consultation);
		consultation.setDoctor(this);

		return consultation;
	}

	public Consultation removeConsultation(Consultation consultation) {
		getConsultations().remove(consultation);
		consultation.setDoctor(null);

		return consultation;
	}

	public List<Specialty> getSpecialties() {
		return this.specialties;
	}

	public void setSpecialties(List<Specialty> specialties) {
		this.specialties = specialties;
	}

}