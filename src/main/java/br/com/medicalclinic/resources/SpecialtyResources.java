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

import br.com.medicalclinic.domain.Specialty;
import br.com.medicalclinic.services.SpecialtyService;

@RestController
@CrossOrigin
@RequestMapping(value = "/medicalClinic/specialties", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
public class SpecialtyResources {

	@Autowired
	private SpecialtyService specialtyService;
	private final CacheControl cacheControl = CacheControl.maxAge(60, TimeUnit.SECONDS);

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Specialty>> list() {
		return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body(specialtyService.list());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> search(@PathVariable("id") Long id) {
		Specialty specialty = specialtyService.search(id);
		return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body(specialty);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> save(@Valid @RequestBody Specialty specialty) {
		specialty = specialtyService.save(specialty);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(specialty.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody Specialty specialty, @PathVariable("id") Long id) {
		specialty.setId(id);
		specialtyService.update(specialty);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		specialtyService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
