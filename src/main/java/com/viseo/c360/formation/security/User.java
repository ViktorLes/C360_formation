package com.viseo.c360.formation.security;

/**
 * Created by TLE3473 on 11/08/2016.
 */
public class User {
    String username;
    long id;
    String role;

    public User() {
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
