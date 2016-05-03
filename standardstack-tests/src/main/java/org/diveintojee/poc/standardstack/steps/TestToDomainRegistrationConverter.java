package org.diveintojee.poc.standardstack.steps;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TestToDomainRegistrationConverter implements Converter< Registration, org.diveintojee.poc.standardstack.domain.Registration > {

	@Override
	public org.diveintojee.poc.standardstack.domain.Registration convert(Registration source) {
		org.diveintojee.poc.standardstack.domain.Registration target = new org.diveintojee.poc.standardstack.domain.Registration();
		target.setEmail(source.getEmail());
		target.setExpires(source.getExpires());
		target.setFirstName(source.getFirstName());
		target.setLastName(source.getLastName());
		target.setToken(source.getToken());
		return target;
	}
}
