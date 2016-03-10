package com.viseo.c360.formation.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity
public class Message {
	
	
	
	@Id
	@GeneratedValue
	long id;
	
	@Version
	long version;
	
	String message;
	

	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Message(String message) {
		super();
		this.message = message;
	}
	
	
	
}
