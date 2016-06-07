package org.diveintojee.poc.standardstack.service;

import java.time.LocalDateTime;

import org.diveintojee.poc.standardstack.domain.Account;
import org.diveintojee.poc.standardstack.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Long save(final Account account) {
        account.setCreated(LocalDateTime.now());
        return accountRepository.save(account).getId();
    }

    public Account getOne(Long id) {
        return accountRepository.findOne(id);
    }
}
