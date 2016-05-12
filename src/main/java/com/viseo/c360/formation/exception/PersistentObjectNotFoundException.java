package com.viseo.c360.formation.exception;

public class PersistentObjectNotFoundException extends Exception{
    public PersistentObjectNotFoundException(){}
    public PersistentObjectNotFoundException(long id, String type){
        super("No one "+type+" with id : '"+Long.toString(id)+"' has been found in database.");
    }
}
