package com.viseo.c360.formation.dao;

import java.util.Collection;
import java.util.List;
import java.util.Date;

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
	
	public boolean isThereOneSessionFormationAlreadyPlanned(SessionFormation sf){

		Collection<SessionFormation> list = null;
		CriteriaBuilder cb = em.getCriteriaBuilder();
		 
		  CriteriaQuery<SessionFormation> q = cb.createQuery(SessionFormation.class);
		  Root<SessionFormation> c = q.from(SessionFormation.class);
		  //ParameterExpression<String> p = cb.parameter(String.class);
		  
		  q.select(c).where(cb.equal(c.get("formation"), sf.getFormation().getId()),
				  cb.or(
					  cb.and(
						  cb.greaterThanOrEqualTo(c.<Date>get("debut"), sf.getDebut()),
						  cb.lessThan(c.<Date>get("debut"), sf.getFin())
						  ),
					  cb.and(
							  cb.greaterThan(c.<Date>get("fin"), sf.getDebut()),
							  cb.lessThanOrEqualTo(c.<Date>get("fin"), sf.getFin())
						  ),
					  cb.and(
							  cb.lessThanOrEqualTo(c.<Date>get("debut"), sf.getDebut()),
							  cb.greaterThanOrEqualTo(c.<Date>get("fin"), sf.getFin())
						  )
					  )
		  );
		  
		  list = (Collection<SessionFormation>) em.createQuery(q).getResultList();
		  
		return !list.isEmpty();
	}
	
	public boolean hasCorrectDates(SessionFormation mySessionFormation){
		return mySessionFormation.getDebut().before(mySessionFormation.getFin());
	}
}
