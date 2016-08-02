package com.viseo.c360.formation.dao;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Service;

@Service
public class ExceptionUtil {

    public String getUniqueField(ConstraintViolationException pe){
        String message = pe.getMessage().substring(pe.getMessage().indexOf("La clé ") + 10);
        return message.substring(0,message.indexOf(")"));
    }



}
