package br.com.medicalclinic.resources;

import java.net.URI;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.medicalclinic.domain.Patient;
import br.com.medicalclinic.services.PatientService;

@RestController
@RequestMapping(value = "/patients", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
public class PatientResources {

	@Autowired
	private PatientService patientService;
	private final CacheControl cacheControl = CacheControl.maxAge(60, TimeUnit.SECONDS);

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Patient>> list() {
		return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body(patientService.list());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> search(@PathVariable("id") Long id) {
		Patient patient = patientService.search(id);
		return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body(patient);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> save(@Valid @RequestBody Patient patient) {
		patient = patientService.save(patient);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(patient.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody Patient patient, @PathVariable("id") Long id) {
		patient.setId(id);
		patientService.update(patient);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		patientService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
