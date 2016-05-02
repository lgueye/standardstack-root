package org.diveintojee.poc.standardstack.steps;

import java.time.LocalDateTime;

public class Account {
    private String email;
    private String firstName;
    private String lastName;
    private LocalDateTime created;
    private LocalDateTime closed;

    public boolean isActive() {
        return created != null && created.isBefore(LocalDateTime.now()) && closed == null;
    }
}
