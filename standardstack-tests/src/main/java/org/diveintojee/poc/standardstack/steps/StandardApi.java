package org.diveintojee.poc.standardstack.steps;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class StandardApi {

	@Value("$registration.resource.url")
	private URI registrationResourceUrl;

	@Autowired
	private RestTemplate api;

	public Registration register(Registration registration) {
		final HttpEntity<Registration> request = new HttpEntity<>(registration);
		request.getHeaders().add(CONTENT_TYPE, APPLICATION_JSON_VALUE);
		URI location = api.postForLocation(registrationResourceUrl, request);
		return api.getForObject(location, Registration.class);
	}

	public Registration loadRegistration(String email) {
		throw new UnsupportedOperationException();
	}

	public Account confirmRegistration(String email) {
		throw new UnsupportedOperationException();
	}
}
