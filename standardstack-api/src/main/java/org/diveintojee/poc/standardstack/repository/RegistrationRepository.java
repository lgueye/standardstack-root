package org.diveintojee.poc.standardstack.repository;

import org.diveintojee.poc.standardstack.domain.Registration;
import org.springframework.data.repository.CrudRepository;

public interface RegistrationRepository extends CrudRepository<Registration, String> {
	Registration findOneByEmail(String email);
}
