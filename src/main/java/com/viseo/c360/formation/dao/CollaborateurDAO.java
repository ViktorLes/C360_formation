package com.viseo.c360.formation.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.viseo.c360.formation.domain.collaborateur.Collaborateur;

@Repository
public class CollaborateurDAO {
	
	@PersistenceContext
	EntityManager em;
	
	@Transactional
	public void addCollaborateur(String matricule, String nom, String prenom){
		
		Collaborateur c = new Collaborateur();
		c.setMatricule(matricule);
		c.setNom(nom);
		c.setPrenom(prenom);
		
		em.persist(c);
	}
	
	@Transactional
	public void addCollaborateur(Collaborateur c){
		em.persist(c);
	}
	
}
