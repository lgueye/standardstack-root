package org.diveintojee.poc.standardstack.api;

import java.net.URI;

import org.diveintojee.poc.standardstack.domain.Registration;
import org.diveintojee.poc.standardstack.service.RegistrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * @author louis.gueye@gmail.com
 */
@RestController
@RequestMapping("/api/registrations")
public class RegistrationResource {
	private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationResource.class);

	@Autowired
	private RegistrationService service;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> create(@RequestBody Registration registration) {
		LOGGER.debug("Attempt to create registration: {}", registration);
		// Save registration and get new token
		String token = this.service.save(registration);
		LOGGER.debug("Saved registration with token: {}", token);

		// Build URI
		final URI location =
				ServletUriComponentsBuilder.fromCurrentServletMapping().path("/api/registrations/{token}").build().expand(token).toUri();
		LOGGER.debug("New resource can be found at : {}", location.toString());

		// Add uri location
		final HttpHeaders headers = new HttpHeaders();
		headers.setLocation(location);

		// Add header to response
		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{token}", method = RequestMethod.POST)
	public ResponseEntity<Void> confirm(@PathVariable("token") String token) {
		LOGGER.debug("Attempt to confirm registration: {}", token);
		// Save registration and get new token
		String token = this.service.save(registration);
		LOGGER.debug("Saved registration with token: {}", token);

		// Build URI
		final URI location =
				ServletUriComponentsBuilder.fromCurrentServletMapping().path("/api/registrations/{token}").build().expand(token).toUri();
		LOGGER.debug("New resource can be found at : {}", location.toString());

		// Add uri location
		final HttpHeaders headers = new HttpHeaders();
		headers.setLocation(location);

		// Add header to response
		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}

	//	@RequestMapping(method = RequestMethod.GET)
	//	public List<Registration> findAll() {
	//		LOGGER.debug("Searching all registrations");
	//		return this.service.findAll();
	//	}
	//
	//	@RequestMapping(value = "/search", method = RequestMethod.GET)
	//	public List<Registration> search(@RequestParam("q") String query) {
	//		LOGGER.debug("Searching registrations for which title or description contains {}", query);
	//		return this.service.search(query);
	//	}

	@RequestMapping(value = "/{token}", method = RequestMethod.GET)
	public Registration get(@PathVariable("token") String token) {
		Registration registration = this.service.getOne(token);
		if (registration == null) {
			final String message = "Registration with token {" + token + "} was not found";
			LOGGER.debug(message);
			throw new ResourceNotFoundException(message);
		}
		LOGGER.debug("Found registration with token: {}", token);
		return registration;
	}
	//
	//	@RequestMapping(value = "/{token}", method = RequestMethod.PUT)
	//	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	//	public void update(@PathVariable("token") String token, @RequestBody Registration registration) {
	//		service.update(token, registration);
	//		LOGGER.debug("Updated registration with token: {}", token);
	//	}

	@RequestMapping(value = "/{token}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("token") String token) {
		this.service.delete(token);
		LOGGER.debug("Deleted registration with token: {}", token);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Registration not found")
	public void notFound() {
	}

	@RequestMapping(method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void delete() {
		this.service.delete();
		LOGGER.debug("Deleted all registrations");
	}

}
