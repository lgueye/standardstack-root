package org.diveintojee.poc.standardstack.api;

import org.diveintojee.poc.standardstack.domain.Account;
import org.diveintojee.poc.standardstack.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author louis.gueye@gmail.com
 */
@RestController
@RequestMapping("/api/accounts")
public class AccountResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountResource.class);

    @Autowired
    private AccountService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Account get(@PathVariable("id") Long id) {
        Account account = this.service.getOne(id);
        if (account == null) {
            final String message = "Account with id {" + id + "} was not found";
            LOGGER.debug(message);
            throw new ResourceNotFoundException(message);
        }
        LOGGER.debug("Found account with id: {}", id);
        return account;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Account not found")
    public void notFound() {
    }

}
