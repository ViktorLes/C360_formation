package com.viseo.c360.formation.exceptions;

import com.viseo.c360.formation.exceptions.dao.UniqueFieldErrors;

public class C360Exception extends RuntimeException{

    UniqueFieldErrors uniqueFieldErrors;

    public C360Exception() {
    }

    public C360Exception(String message) {
        super(message);
    }

    public C360Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public C360Exception(Throwable cause) {
        super(cause);
    }

    public C360Exception(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public C360Exception(UniqueFieldErrors uniqueFieldErrors) {
        this.uniqueFieldErrors = uniqueFieldErrors;
    }

    public UniqueFieldErrors getUniqueFieldErrors() {
        return uniqueFieldErrors;
    }
}
