package com.example.chatBackend.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
@Entity
public class UserStatus {

    @Id
    private String username; // Primary Key

    private boolean isOnline;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline =online;
}
}
