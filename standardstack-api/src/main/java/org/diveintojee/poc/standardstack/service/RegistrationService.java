package org.diveintojee.poc.standardstack.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.diveintojee.poc.standardstack.domain.Registration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.common.base.MoreObjects;

@Component
public class RegistrationService {

	private Map<String, Registration> repository = new HashMap<>();

	@Value("${token.retention.in.days}")
	private Integer tokenRetentionInDays;

	public String save(Registration registration) {
		registration.setExpires(LocalDateTime.now().plusDays(tokenRetentionInDays));
		String token = DigestUtils.sha1Hex(
				MoreObjects
						.toStringHelper(registration)
						.add("expires", registration.getExpires().format(DateTimeFormatter.ISO_DATE))
						.add("email", registration.getEmail())
						.add("firstName", registration.getFirstName())
						.add("lastName", registration.getLastName()).toString());
		registration.setToken(token);
		repository.put(token, registration);
		return token;
	}

	public Registration getOne(String token) {
		return repository.get(token);
	}

	public void delete() {
		repository.clear();
	}

	public void delete(String token) {
		repository.remove(token);
	}

}
