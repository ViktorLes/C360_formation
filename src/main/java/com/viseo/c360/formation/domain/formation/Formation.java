package com.viseo.c360.formation.domain.formation;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

@Entity
public class Formation {

		@Id
		@GeneratedValue
		long id;
		
		@Version
		long version;
		
		@NotNull
		String titreformation;
		
		@NotNull
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
