package com.viseo.c360.formation.exceptions.security;

import com.viseo.c360.formation.exceptions.C360Exception;

public class JwtTokenMalformedException extends C360Exception {

    public JwtTokenMalformedException() {
    }

    public JwtTokenMalformedException(String s) {
        super(s);
    }
}
