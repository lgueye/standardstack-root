package org.diveintojee.poc.standardstack.service;

import java.time.LocalDateTime;

import org.diveintojee.poc.standardstack.domain.Account;
import org.diveintojee.poc.standardstack.domain.Registration;
import org.diveintojee.poc.standardstack.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RegistrationService {

    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private AccountService accountService;

    @Value("${token.retention.in.days}")
    private Integer tokenRetentionInDays;

    @Autowired
    private RegistrationToAccountConverter registrationToAccountConverter;

    public String save(Registration registration) {
        registration.setExpires(LocalDateTime.now().plusDays(tokenRetentionInDays));
        String token = registration.generateToken();
        registration.setToken(token);
        registrationRepository.save(registration);
        return token;
    }

    public Registration getOne(String token) {
        return registrationRepository.findOne(token);
    }

    public Registration findByEmail(String email) {
        return registrationRepository.findOneByEmail(email);
    }

    public Account confirm(String token) {
        Registration registration = getOne(token);
        registration.archive();
        registrationRepository.save(registration);
        Account account = registrationToAccountConverter.convert(registration);
        accountService.save(account);
        return account;
    }
}
