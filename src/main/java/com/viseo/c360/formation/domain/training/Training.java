package com.viseo.c360.formation.domain.training;

import com.viseo.c360.formation.domain.BaseEntity;

import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
public class Training extends BaseEntity {

	public static class Regex{
		public static final String TRAINING_TITLE = "[a-zA-Z0-9+#'-. áàâäãåçéèêëíìîïñóòôöõúùûüýÿæ\u0153ÁÀÂÄÃÅÇÉÈÊËÍÌÎÏÑÓÒÔÖÕÚÙÛÜÝ\u0178Æ\u0152]";
		public static final String NUMBER_HALF_DAYS = "[0-9]";
	}

	@NotNull
	@Pattern(regexp= Training.Regex.TRAINING_TITLE +"*")
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
