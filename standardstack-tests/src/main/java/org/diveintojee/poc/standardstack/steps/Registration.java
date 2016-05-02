package org.diveintojee.poc.standardstack.steps;

import org.jbehave.core.annotations.AsParameters;
import org.jbehave.core.annotations.Parameter;

import java.time.LocalDateTime;

@AsParameters
public class Registration {
    private String tokenUrl;
    private LocalDateTime expires;
    private String email;
    @Parameter(name = "first_name")
    private String firstName;
    @Parameter(name = "last_name")
    private String lastName;

    public String getTokenUrl() {
        return tokenUrl;
    }

    public void setTokenUrl(String tokenUrl) {
        this.tokenUrl = tokenUrl;
    }

    public LocalDateTime getExpires() {
        return expires;
    }

    public void setExpires(LocalDateTime expires) {
        this.expires = expires;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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
