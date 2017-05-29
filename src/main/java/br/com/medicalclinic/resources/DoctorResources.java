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

import br.com.medicalclinic.domain.Doctor;
import br.com.medicalclinic.services.DoctorService;

@RestController
@RequestMapping(value = "/medicalClinic/doctors", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
public class DoctorResources {

	@Autowired
	private DoctorService doctorService;
	private final CacheControl cacheControl = CacheControl.maxAge(60, TimeUnit.SECONDS);

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Doctor>> list() {
		return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body(doctorService.list());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> search(@PathVariable("id") Long id) {
		Doctor doctor = doctorService.search(id);
		return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body(doctor);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> save(@Valid @RequestBody Doctor doctor) {
		doctor = doctorService.save(doctor);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(doctor.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody Doctor doctor, @PathVariable("id") Long id) {
		doctor.setId(id);
		doctorService.update(doctor);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		doctorService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
