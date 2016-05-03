package com.viseo.c360.formation.domain.formation;

import com.viseo.c360.formation.domain.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
public class Formation extends BaseEntity {

	public static class Regex{
		public static final String TITRE_FORMATION = "[a-zA-Z0-9+#'-. áàâäãåçéèêëíìîïñóòôöõúùûüýÿæ\u0153ÁÀÂÄÃÅÇÉÈÊËÍÌÎÏÑÓÒÔÖÕÚÙÛÜÝ\u0178Æ\u0152]";
		public static final String NOMBRE_DEMI_JOURNEE = "[0-9]";
	}

	@NotNull
	@Pattern(regexp=Formation.Regex.TITRE_FORMATION+"*")
	String titreFormation;

	@NotNull
	@Min(value = 1)
	@Max(value = 200)
	int nombreDemiJournee;

	public Formation() {
	}

	public String getTitreFormation() {
		return titreFormation;
	}

	public void setTitreFormation(String titreFormation) {
		this.titreFormation = titreFormation;
	}

	public int getNombreDemiJournee() {
		return nombreDemiJournee;
	}

	public void setNombreDemiJournee(int nombreDemiJournee) {
		this.nombreDemiJournee = nombreDemiJournee;
	}
}
