package org.diveintojee.poc.standardstack.steps;

import org.jbehave.core.annotations.AsParameters;
import org.jbehave.core.annotations.Parameter;

@AsParameters
public class Registration {
	@Parameter(name = "first_name")
	private String firstName;
	@Parameter(name = "last_name")
	private String lastName;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
