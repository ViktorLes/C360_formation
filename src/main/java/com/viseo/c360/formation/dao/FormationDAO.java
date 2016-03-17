package com.viseo.c360.formation.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.viseo.c360.formation.domain.formation.Formation;

@Repository
public class FormationDAO {
	
	@PersistenceContext
	EntityManager enm;
	
	@Transactional
	public void addFormation(String titreformation,int nombredemijournee){
		
		Formation F = new Formation();
		F.setTitreformation(titreformation);
		F.setNombredemijournee(nombredemijournee);
		
		enm.persist(F);
	}
	
	@Transactional
	public void addFormation(Formation F){
		enm.persist(F);
}
}
