package org.diveintojee.poc.standardstack.steps;

import java.util.List;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Pending;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TwoPhasesRegistrationSteps {

	private static final Logger LOG = LoggerFactory.getLogger(TwoPhasesRegistrationSteps.class);

	@Given("visitor $email intends to register with the following information: $rows")
	@Pending
	public void buildProfile(String email, List<AccountRow> accountRows) {
		LOG.debug("Building profile for account {}", email);
		throw new UnsupportedOperationException();
	}

	@When("visitor <email> registers")
	@Pending
	public void register(String email) {
		LOG.debug("Running registration process for account {}", email);
		throw new UnsupportedOperationException();
	}

	@Then("visitor $email draft account is persisted with the following information: $rows")
	@Pending
	public void profileIsPersisted(String email, List<AccountRow> accountRows) {
		LOG.debug("Account {} was properly persisted", email);
		throw new UnsupportedOperationException();
	}

	@Then("visitor <email> gets the following token <token> to confirm registration")
	@Pending
	public void confirmationTokenIsGenerated(String email, String token) {
		LOG.debug("Confirmation token {} was generated for account {}", token, email);
		throw new UnsupportedOperationException();
	}

}
