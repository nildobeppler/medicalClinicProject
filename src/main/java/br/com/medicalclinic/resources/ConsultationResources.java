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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.medicalclinic.domain.Consultation;
import br.com.medicalclinic.services.ConsultationService;

@RestController
@RequestMapping(value = "/medicalClinic/consultations", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
public class ConsultationResources {

	@Autowired
	private ConsultationService consultationService;
	private final CacheControl cacheControl = CacheControl.maxAge(60, TimeUnit.SECONDS);

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Consultation>> list() {
		return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body(consultationService.list());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> search(@PathVariable("id") Long id) {
		Consultation consultation = consultationService.search(id);
		return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body(consultation);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> save(@Valid @RequestBody Consultation consultation) {
		consultation = consultationService.save(consultation);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(consultation.getId()) .toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody Consultation consultation, @PathVariable("id") Long id) {
		consultation.setId(id);
		consultationService.update(consultation);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@CrossOrigin
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		consultationService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
