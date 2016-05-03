package org.diveintojee.poc.standardstack.steps;

import org.diveintojee.poc.standardstack.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static java.util.Collections.singletonList;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
public class StandardApi {

    @Autowired
    private RestTemplate api;

    @Autowired
    private DomainToTestRegistrationConverter domainToTestRegistrationConverter;
    @Autowired
    private TestToDomainRegistrationConverter testToDomainRegistrationConverter;

    public Registration register(Registration registration) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(APPLICATION_JSON);
        final HttpEntity<org.diveintojee.poc.standardstack.domain.Registration> request =
                new HttpEntity<>(testToDomainRegistrationConverter.convert(registration), headers);
        URI location = api.postForLocation("http://localhost:9090/standardstack/api/registrations", request);
        return domainToTestRegistrationConverter.convert(
                loadByLocation(location, org.diveintojee.poc.standardstack.domain.Registration.class));
    }

    public <T> T loadByLocation(URI uri, Class<T> clazz) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(singletonList(APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        final ResponseEntity<T> responseEntity = api.exchange(uri, HttpMethod.GET, entity, clazz);
        return responseEntity.getBody();
    }

    public Registration loadRegistrationByEmail(String email) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(singletonList(APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        final String url = "http://localhost:9090/standardstack/api/registrations?email=" + email;
        final ResponseEntity<org.diveintojee.poc.standardstack.domain.Registration> responseEntity = api
                .exchange(URI.create(url), HttpMethod.GET, entity, org.diveintojee.poc.standardstack.domain.Registration.class);
        return domainToTestRegistrationConverter.convert(responseEntity.getBody());
    }

    public Account confirmRegistration(String email) {
        URI location = api.postForLocation(URI.create(loadRegistrationByEmail(email).getTokenUrl()),
                new HttpEntity<>(new HttpHeaders()));
        return loadByLocation(location, Account.class);
    }
}
