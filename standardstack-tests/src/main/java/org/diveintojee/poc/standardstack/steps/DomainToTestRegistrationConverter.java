package org.diveintojee.poc.standardstack.steps;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DomainToTestRegistrationConverter implements Converter<org.diveintojee.poc.standardstack.domain.Registration, Registration> {

	@Override
	public Registration convert(org.diveintojee.poc.standardstack.domain.Registration source) {
		Registration target = new Registration();
		target.setEmail(source.getEmail());
		target.setExpires(source.getExpires());
		target.setFirstName(source.getFirstName());
		target.setLastName(source.getLastName());
		target.setTokenUrl(source.getToken());
		return target;
	}
}
