package org.diveintojee.poc.standardstack.steps;

import org.diveintojee.poc.standardstack.domain.Account;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Pending;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@Component
public class TwoPhasesRegistrationSteps {

    private static final Logger LOG = LoggerFactory.getLogger(TwoPhasesRegistrationSteps.class);

    private Registration registration;
    private Account account;

    @Autowired
    private StandardApi standardApi;

    @Given("visitor $email intends to register with the following information: $rows")
    public void buildProfile(String email, List<Registration> rows) {
        LOG.debug("Building profile for account {}", email);
        registration = rows.iterator().next();
    }

    @When("visitor $email registers")
    public void register(String email) {
        LOG.debug("Running registration process for account {} and details {}", email, registration);
        registration.setEmail(email);
        registration = standardApi.register(registration);
    }

    @Then("visitor $email registration is properly persisted")
    @Pending
    public void registrationIsPersisted(String email) {
        assertEquals(registration, standardApi.loadRegistrationByEmail(email));
        LOG.debug("Registration {} was properly persisted", email);
    }

    @When("visitor $email confirms registration")
    @Pending
    public void confirmRegistration(String email) {
        LOG.debug("Confirming registration process for account {}", email);
        account = standardApi.confirmRegistration(email);
    }

    @Then("visitor $email account is active")
    @Pending
    public void accountIsActive(String email) {
        assertTrue(account.isActive());
        LOG.debug("Account {} is active", email);
    }


}
