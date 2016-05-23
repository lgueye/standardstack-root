package org.diveintojee.poc.standardstack.domain;

import com.google.common.base.MoreObjects;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.apache.commons.codec.digest.DigestUtils;

@Entity
@Table(name = "registrations")
public class Registration {

    public static final int FIRST_NAME_MAX_SIZE = 50;
    public static final int LAST_NAME_MAX_SIZE = 50;
    public static final int EMAIL_MAX_SIZE = 50;
    public static final int TOKEN_MAX_SIZE = 200;

    @Id
    @Size(max = TOKEN_MAX_SIZE)
    private String token;
    private LocalDateTime expires;
    private LocalDateTime archived;
    @Column
    @Size(max = EMAIL_MAX_SIZE)
    private String email;
    @Column(name = "first_name")
    @Size(max = FIRST_NAME_MAX_SIZE)
    private String firstName;
    @Column(name = "last_name")
    @Size(max = LAST_NAME_MAX_SIZE)
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

    public static String generateToken(final Registration registration) {
        final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper(registration);
        if (registration.getExpires() != null) {
            helper.add("expires", registration.getExpires().format(DateTimeFormatter.ISO_DATE));
        }
        return DigestUtils.sha1Hex(helper
                        .add("email", registration.getEmail()).add("firstName", registration.getFirstName())
                        .add("lastName", registration.getLastName()).toString());
    }

    public void archive() {
        setArchived(LocalDateTime.now());
    }
}
