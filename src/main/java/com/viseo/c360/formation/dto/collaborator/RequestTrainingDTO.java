package com.viseo.c360.formation.dto.collaborator;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.viseo.c360.formation.domain.training.Training;
import com.viseo.c360.formation.dto.training.TrainingSessionDTO;

public class RequestTrainingDTO {

	@NotNull
	@Valid
	Training training;

	@NotNull
	@Valid
	List<TrainingSessionDTO> trainingSessions;

	@NotNull
	@Min(value = 1)
	long collaborator;

	public RequestTrainingDTO() {
		super();
	}

	public Training getTraining() {
		return training;
	}
	public void setTraining(Training training) {
		this.training = training;
	}
	public List<TrainingSessionDTO> getTrainingSessions() {
		return trainingSessions;
	}
	public void setTrainingSessions(List<TrainingSessionDTO> trainingSessions) {
		this.trainingSessions = trainingSessions;
	}
	public long getCollaborator() {
		return collaborator;
	}
	public void setCollaborator(long collaborator) {
		this.collaborator = collaborator;
	}
}
