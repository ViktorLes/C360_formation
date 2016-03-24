package com.viseo.c360.formation.domain.formation;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

@Entity
public class SessionFormation {
	
	@Id
	@GeneratedValue
	long id;
	
	@Version
	long version;
	
	@ManyToOne
	Formation formation;
	
//	Date firstDay;
//	
//	Date lastDay;
//	
//	Date firstTime;
//	
//	Date lastTime;
//	
//	String lieu;

	public SessionFormation() {
		super();
	}

	public long getId() {
		return id;
	}

	public long getVersion() {
		return version;
	}

	public Formation getFormation() {
		return formation;
	}
//
//	public Date getFirstDay() {
//		return firstDay;
//	}
//
//	public Date getLastDay() {
//		return lastDay;
//	}
//
//	public Date getFirstTime() {
//		return firstTime;
//	}
//
//	public Date getLastTime() {
//		return lastTime;
//	}
//
//	public String getLieu() {
//		return lieu;
//	}

	public void setId(long id) {
		this.id = id;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public void setFormation(Formation formation) {
		this.formation = formation;
	}

//	public void setFirstDay(Date firstDay) {
//		this.firstDay = firstDay;
//	}
//
//	public void setLastDay(Date lastDay) {
//		this.lastDay = lastDay;
//	}
//
//	public void setFirstTime(Date firstTime) {
//		this.firstTime = firstTime;
//	}
//
//	public void setLastTime(Date lastTime) {
//		this.lastTime = lastTime;
//	}
//
//	public void setLieu(String lieu) {
//		this.lieu = lieu;
//	}
}
