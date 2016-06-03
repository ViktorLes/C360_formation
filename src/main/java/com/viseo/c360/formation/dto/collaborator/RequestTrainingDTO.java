package com.viseo.c360.formation.dto.collaborator;

import java.util.List;

import com.viseo.c360.formation.dto.BaseDTO;
import com.viseo.c360.formation.dto.training.TrainingDTO;
import com.viseo.c360.formation.dto.training.TrainingSessionDTO;


public class RequestTrainingDTO extends BaseDTO{


	TrainingDTO trainingDTO;

	List<TrainingSessionDTO> trainingSessionsDtos;

	CollaboratorDTO collaboratorDTO;

	public RequestTrainingDTO() {
		super();
	}

	public TrainingDTO getTrainingDTO() {
		return trainingDTO;
	}
	public void setTrainingDTO(TrainingDTO trainingDTO) {
		this.trainingDTO = trainingDTO;
	}
	public List<TrainingSessionDTO> getTrainingSessionsDtos() {
		return trainingSessionsDtos;
	}
	public void setTrainingSessionsDtos(List<TrainingSessionDTO> trainingSessions) {
		this.trainingSessionsDtos = trainingSessions;
	}
	public CollaboratorDTO getCollaboratorDTO() { return collaboratorDTO; }
	public void setCollaborator(CollaboratorDTO collaboratorDTO) {
		this.collaboratorDTO = collaboratorDTO;
	}
}
