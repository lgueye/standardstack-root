package org.diveintojee.poc.standardstack.repository;

import org.diveintojee.poc.standardstack.domain.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long> {
}
