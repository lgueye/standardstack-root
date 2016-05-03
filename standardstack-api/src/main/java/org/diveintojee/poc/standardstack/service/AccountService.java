package org.diveintojee.poc.standardstack.service;

import org.diveintojee.poc.standardstack.domain.Account;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class AccountService {

    private Map<Long, Account> accountRepository = new HashMap<>();
    private AtomicLong ids = new AtomicLong();

    public Long save(Account account) {
        account.setCreated(LocalDateTime.now());
        final Long id = ids.incrementAndGet();
        account.setId(id);
        accountRepository.put(id, account);
        return id;
    }

    public Account getOne(Long id) {
        return accountRepository.get(id);
    }
}
