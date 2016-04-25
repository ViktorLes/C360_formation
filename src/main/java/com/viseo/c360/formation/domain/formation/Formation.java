package com.viseo.c360.formation.domain.formation;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
//@JsonSerialize(using = FormationSerializer.class)
public class Formation {

	public static class Regex{
		public static final String titreFormation = "[a-zA-Z.+#0-9-'. áàâäãåçéèêëíìîïñóòôöõúùûüýÿæ\u0153ÁÀÂÄÃÅÇÉÈÊËÍÌÎÏÑÓÒÔÖÕÚÙÛÜÝ\u0178Æ\u0152]";
		public static final String nombreDemiJournee = "[0-9]";
	}
	
		@Id
		@GeneratedValue
		long id;
		
		@Version
		long version;
		
		@NotNull
		@Pattern(regexp=Formation.Regex.titreFormation+"*")
		String titreformation;
		
		@NotNull
		@Min(value = 1)
		@Max(value = 200)
		int nombredemijournee;
		
		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public long getVersion() {
			return version;
		}

		public void setVersion(long version) {
			this.version = version;
		}

		public String getTitreformation() {
			return titreformation;
		}

		public void setTitreformation(String titreformation) {
			this.titreformation = titreformation;
		}

		public int getNombredemijournee() {
			return nombredemijournee;
		}

		public void setNombredemijournee(int nombredemijournee) {
			this.nombredemijournee = nombredemijournee;
		}
}
