package com.viseo.c360.formation.exceptions.security;


import com.viseo.c360.formation.exceptions.C360Exception;

public class JwtTokenMissingException extends C360Exception {

    public JwtTokenMissingException() {
    }

    public JwtTokenMissingException(String s) {
        super(s);
    }
}
