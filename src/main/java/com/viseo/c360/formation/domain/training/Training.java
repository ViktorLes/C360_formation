package com.viseo.c360.formation.domain.training;

import com.viseo.c360.formation.domain.BaseEntity;
import com.viseo.c360.formation.dto.training.TrainingDescription;

import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
public class Training extends BaseEntity {

	@NotNull
	@Pattern(regexp= TrainingDescription.Regex.TRAINING_TITLE+"*")
	String trainingTitle;

	@NotNull
	@Min(value = 1)
	@Max(value = 200)
	int numberHalfDays;

	public Training() {
	}

	public String getTrainingTitle() {
		return trainingTitle;
	}

	public void setTrainingTitle(String trainingTitle) {
		this.trainingTitle = trainingTitle;
	}

	public int getNumberHalfDays() {
		return numberHalfDays;
	}

	public void setNumberHalfDays(int numberHalfDays) {
		this.numberHalfDays = numberHalfDays;
	}
}

