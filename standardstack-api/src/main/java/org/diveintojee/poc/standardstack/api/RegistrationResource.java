package org.diveintojee.poc.standardstack.api;

import org.diveintojee.poc.standardstack.domain.Account;
import org.diveintojee.poc.standardstack.domain.Registration;
import org.diveintojee.poc.standardstack.service.RegistrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

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
    public ResponseEntity<Account> confirm(@PathVariable("token") String token) {
        LOGGER.debug("Attempt to confirm registration: {}", token);
        // Save registration and get new token
        Account account = this.service.confirm(token);
        LOGGER.debug("Archived registration {} and created account: {}", token, account);

        // Build URI
        final URI location =
                ServletUriComponentsBuilder.fromCurrentServletMapping().path("/api/accounts/{id}").build().expand(account.getId()).toUri();
        LOGGER.debug("New resource can be found at : {}", location.toString());

        // Add uri location
        final HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);

        // Add header to response
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    public Registration findByEmail(@RequestParam("email") String email) {
        LOGGER.debug("Searching registrations for which emails equals {}", email);
        return this.service.findByEmail(email);
    }

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

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Registration not found")
    public void notFound() {
    }

}
