package com.viseo.c360.formation.domain.formation;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity
public class Session {
	
	@Id
	@GeneratedValue
	long id;
	
	@Version
	long version;
	
	
	long id_formation;
	
	Date firstDay;
	
	Date lastDay;
	
	Date firstTime;
	
	Date lastTime;
	
	String lieu;
}
