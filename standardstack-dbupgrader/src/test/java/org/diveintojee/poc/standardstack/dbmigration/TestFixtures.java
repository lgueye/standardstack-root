package org.diveintojee.poc.standardstack.dbmigration;

import java.time.LocalDateTime;

import org.apache.commons.lang3.RandomStringUtils;
import org.diveintojee.poc.standardstack.domain.Registration;

public class TestFixtures {

	public static Registration validRegistration() {
		final Registration registration = new Registration();
		registration.setExpires(LocalDateTime.now().plusDays(3));
		registration.setFirstName(RandomStringUtils.randomAlphanumeric(Registration.FIRST_NAME_MAX_SIZE));
		registration.setLastName(RandomStringUtils.randomAlphanumeric(Registration.LAST_NAME_MAX_SIZE));
		registration.setEmail(validEmail());
		registration.setToken(registration.generateToken());
		return registration;
	}

	public static String validEmail() {
		String x = RandomStringUtils.random(15);
		String y = RandomStringUtils.random(15);
		String z = RandomStringUtils.random(10);
		return String.format("%s.%s@%s.net", x, y, z);
	}

}
