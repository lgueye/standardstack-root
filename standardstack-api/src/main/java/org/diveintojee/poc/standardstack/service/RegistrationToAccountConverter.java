package org.diveintojee.poc.standardstack.service;

import org.diveintojee.poc.standardstack.domain.Account;
import org.diveintojee.poc.standardstack.domain.Registration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RegistrationToAccountConverter implements Converter<Registration, Account> {

	@Override
	public Account convert(Registration source) {
		Account target = new Account();
		target.setEmail(source.getEmail());
		target.setFirstName(source.getFirstName());
		target.setLastName(source.getLastName());
		return target;
	}
}
