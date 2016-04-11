package com.viseo.c360.formation.dao;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.viseo.c360.formation.domain.formation.Formation;
import com.viseo.c360.formation.domain.formation.SessionFormation;

@Repository
public class FormationDAO {
	
	@PersistenceContext
	EntityManager em;
	
	
	/*** Formation ***/
	
	@Transactional
	public Formation getFormation(long id){
		return em.find(Formation.class, id);
	}

	@Transactional
	public void addFormation(String titreformation,int nombredemijournee){
		
		Formation F = new Formation();
		F.setTitreformation(titreformation);
		F.setNombredemijournee(nombredemijournee);
		em.persist(F);
	}
	
	@Transactional
	public void addFormation(Formation F){
		em.persist(F);
	}
	
	
	public boolean isFormationAlreadySaved(String titreFormation){

		Collection<Formation> list = null;
		CriteriaBuilder cb = em.getCriteriaBuilder();
		 
		  CriteriaQuery<Formation> q = cb.createQuery(Formation.class);
		  Root<Formation> c = q.from(Formation.class);
		  //ParameterExpression<String> p = cb.parameter(String.class);
		  q.select(c).where(cb.equal(c.get("titreformation"), titreFormation));
		  
		  list = (Collection<Formation>) em.createQuery(q).getResultList();
		  
		return !list.isEmpty(); //return true if the list is not avoid
	}
	
	
	/*** Session Formation ***/
	
	@Transactional
	public void addSessionFormation(SessionFormation sf){
		em.persist(sf);
	}
	
	public List<Formation> GetAllFormation() {
		return em.createQuery("select a from Formation a", Formation.class).getResultList();
	}
	
	@Transactional
	public SessionFormation getSessionFormation(long id) {
		return em.find(SessionFormation.class, id);
	}
	
	
}
