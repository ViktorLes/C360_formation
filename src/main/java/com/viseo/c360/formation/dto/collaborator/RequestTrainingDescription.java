package com.viseo.c360.formation.dto.collaborator;

import java.util.List;

import com.viseo.c360.formation.dto.BaseDTO;
import com.viseo.c360.formation.dto.training.TrainingDescription;
import com.viseo.c360.formation.dto.training.TrainingSessionDescription;


public class RequestTrainingDescription extends BaseDTO{


	TrainingDescription trainingDescription;

	List<TrainingSessionDescription> trainingSessionsDtos;

	CollaboratorDescription collaboratorDescription;

	public RequestTrainingDescription() {
		super();
	}

	public TrainingDescription getTrainingDescription() {
		return trainingDescription;
	}
	public void setTrainingDescription(TrainingDescription trainingDescription) {
		this.trainingDescription = trainingDescription;
	}
	public List<TrainingSessionDescription> getTrainingSessionsDescriptions() {
		return trainingSessionsDtos;
	}
	public void setTrainingSessionsDtos(List<TrainingSessionDescription> trainingSessions) {
		this.trainingSessionsDtos = trainingSessions;
	}
	public CollaboratorDescription getCollaboratorDescription() { return collaboratorDescription; }
	public void setCollaborator(CollaboratorDescription collaboratorDescription) {
		this.collaboratorDescription = collaboratorDescription;
	}
}
