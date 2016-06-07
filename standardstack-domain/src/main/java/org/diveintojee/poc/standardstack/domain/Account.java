package org.diveintojee.poc.standardstack.domain;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private LocalDateTime created;
    private LocalDateTime closed;

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

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getClosed() {
        return closed;
    }

    public void setClosed(LocalDateTime closed) {
        this.closed = closed;
    }

    public boolean isActive() {
        return created != null && created.isBefore(LocalDateTime.now()) && closed == null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equal(id, account.id) &&
                Objects.equal(email, account.email) &&
                Objects.equal(firstName, account.firstName) &&
                Objects.equal(lastName, account.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, email, firstName, lastName);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("id", id).add("email", email).add("firstName", firstName).add("lastName", lastName)
                .add("created", created).add("closed", closed).toString();
    }
}
