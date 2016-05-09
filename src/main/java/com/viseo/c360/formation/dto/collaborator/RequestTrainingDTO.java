package com.viseo.c360.formation.dto.collaborator;

import java.util.List;

import javax.inject.Inject;

import com.viseo.c360.formation.dao.CollaboratorDAO;
import com.viseo.c360.formation.dao.TrainingDAO;
import com.viseo.c360.formation.domain.collaborator.Collaborator;
import com.viseo.c360.formation.domain.collaborator.RequestTraining;

public class RequestTrainingDTO {
	long training;
	List<Long> trainingSessions;
	long collaborator;
	public RequestTrainingDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public long getTraining() {
		return training;
	}
	public void setTraining(long training) {
		this.training = training;
	}
	public List<Long> getTrainingSessions() {
		return trainingSessions;
	}
	public void setTrainingSessions(List<Long> trainingSessions) {
		this.trainingSessions = trainingSessions;
	}
	public long getCollaborator() {
		return collaborator;
	}
	public void setCollaborator(long collaborator) {
		this.collaborator = collaborator;
	}
}
