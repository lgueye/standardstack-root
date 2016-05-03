package org.diveintojee.poc.standardstack.domain;

import com.google.common.base.MoreObjects;

import java.time.LocalDateTime;

public class Registration {
    private String token;
    private LocalDateTime expires;
    private LocalDateTime archived;
    private String email;
    private String firstName;
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

    public LocalDateTime getArchived() {
        return archived;
    }

    public void setArchived(LocalDateTime archived) {
        this.archived = archived;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("token", token)
                .add("expires", expires)
                .add("email", email)
                .add("firstName", firstName)
                .add("lastName", lastName)
                .add("archived", archived).toString();
    }

    public void archive() {
        setArchived(LocalDateTime.now());
    }
}
