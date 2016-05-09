package com.viseo.c360.formation.dto.collaborator;

import java.util.List;

public class RequestTraining {
	long id;
	long training;
	List<Long> s;
	long collaborator;
	public RequestTraining() {
		super();
		// TODO Auto-generated constructor stub
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getTraining() {
		return training;
	}
	public void setTraining(long training) {
		this.training = training;
	}
	public List<Long> getS() {
		return s;
	}
	public void setS(List<Long> s) {
		this.s = s;
	}
	public long getCollaborator() {
		return collaborator;
	}
	public void setCollaborator(long collaborator) {
		this.collaborator = collaborator;
	}
}
