package com.viseo.c360.formation.dto.collaborator;

import java.util.List;

import javax.inject.Inject;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.viseo.c360.formation.dao.CollaboratorDAO;
import com.viseo.c360.formation.dao.TrainingDAO;
import com.viseo.c360.formation.domain.collaborator.Collaborator;
import com.viseo.c360.formation.domain.collaborator.RequestTraining;

public class RequestTrainingDTO {
	@NotNull
	@Min(value = 1)
	long training;

	List<Long> trainingSessions;

	@NotNull
	@Min(value = 1)
	long collaborator;

	public RequestTrainingDTO() {
		super();
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