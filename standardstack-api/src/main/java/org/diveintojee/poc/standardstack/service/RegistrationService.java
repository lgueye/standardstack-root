package org.diveintojee.poc.standardstack.service;

import com.google.common.base.MoreObjects;
import org.apache.commons.codec.digest.DigestUtils;
import org.diveintojee.poc.standardstack.domain.Account;
import org.diveintojee.poc.standardstack.domain.Registration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Component
public class RegistrationService {

    private Map<String, Registration> registrationRepository = new HashMap<>();

    @Autowired
    private AccountService accountService;

    @Value("${token.retention.in.days}")
    private Integer tokenRetentionInDays;

    @Autowired
    private RegistrationToAccountConverter registrationToAccountConverter;

    public String save(Registration registration) {
        registration.setExpires(LocalDateTime.now().plusDays(tokenRetentionInDays));
        String token = Registration.generateToken(registration);
        registration.setToken(token);
        registrationRepository.put(token, registration);
        return token;
    }

    public Registration getOne(String token) {
        return registrationRepository.get(token);
    }

    public Registration findByEmail(String email) {
        return registrationRepository.values()
                .stream()
                .filter(registration -> email.equals(registration.getEmail()))
                .findFirst().get();
    }

    public Account confirm(String token) {
        Registration registration = getOne(token);
        registration.archive();
        registrationRepository.put(token, registration);
        Account account = registrationToAccountConverter.convert(registration);
        accountService.save(account);
        return account;
    }
}
