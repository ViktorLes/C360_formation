package com.viseo.c360.formation.security;


import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.security.auth.Subject;
import java.util.Collection;

public class JwtAuthenticationToken implements Authentication {

    public JwtAuthenticationToken() {
    }

    public JwtAuthenticationToken(UsernamePasswordAuthenticationToken authentication) {
       // authentication.
    }

    public JwtAuthenticationToken(String token) {
        //IMPLEMENTATION IS MISSING
    }

    String token;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public boolean isAuthenticated() {
        return false;
    }

    @Override
    public void setAuthenticated(boolean b) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean implies(Subject subject) {
        return false;
    }

    public String getToken() {
        return this.token;
    }
}
