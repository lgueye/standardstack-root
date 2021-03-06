package org.diveintojee.poc.standardstack.steps;

import org.diveintojee.poc.standardstack.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${app.host}")
    private String host;

    @Value("${app.port}")
    private int port;

    @Value("${app.context}")
    private String context;

    @Autowired
    private DomainToTestRegistrationConverter domainToTestRegistrationConverter;
    @Autowired
    private TestToDomainRegistrationConverter testToDomainRegistrationConverter;

    public Registration register(Registration registration) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(APPLICATION_JSON);
        final HttpEntity<org.diveintojee.poc.standardstack.domain.Registration> request =
                new HttpEntity<>(testToDomainRegistrationConverter.convert(registration), headers);
        URI location = api.postForLocation(apiUrl() + "/registrations", request);
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
        final String url = apiUrl() + "/registrations?email=" + email;
        final ResponseEntity<org.diveintojee.poc.standardstack.domain.Registration> responseEntity = api
                .exchange(URI.create(url), HttpMethod.GET, entity, org.diveintojee.poc.standardstack.domain.Registration.class);
        return domainToTestRegistrationConverter.convert(responseEntity.getBody());
    }

    public Account confirmRegistration(String email) {
        final String token = loadRegistrationByEmail(email).getToken();
        final String url = apiUrl() + "/registrations/" + token;
        URI location = api.postForLocation(url, new HttpEntity<>(new HttpHeaders()));
        return loadByLocation(location, Account.class);
    }

    private String apiUrl() {
        return String.format("http://%s:%s/%s/api", host, port, context);
    }
}
