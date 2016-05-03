package org.diveintojee.poc.standardstack.steps;

import java.time.LocalDateTime;

import com.google.common.base.Objects;
import org.jbehave.core.annotations.AsParameters;
import org.jbehave.core.annotations.Parameter;

@AsParameters
public class Registration {
    private String token;
    private LocalDateTime expires;
    private String email;
    @Parameter(name = "first_name")
    private String firstName;
    @Parameter(name = "last_name")
    private String lastName;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Registration that = (Registration) o;
        return Objects.equal(token, that.token) &&
                Objects.equal(email, that.email) &&
                Objects.equal(firstName, that.firstName) &&
                Objects.equal(lastName, that.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(token, email, firstName, lastName);
    }
}
