package com.viseo.c360.formation.dao;

import java.util.Collection;
import java.util.List;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import com.viseo.c360.formation.domain.formation.Training;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.viseo.c360.formation.domain.formation.SessionFormation;

@Repository
public class FormationDAO {
	
	@PersistenceContext
	EntityManager em;
	
	
	/*** Training ***/
	
	@Transactional
	public Training getFormation(long id){
		return em.find(Training.class, id);
	}

	@Transactional
	public void addFormation(String titleCourse,int numberHalfDays){
		
		Training F = new Training();
		F.setTitleTraining(titleCourse);
		F.setNumberHalfDays(numberHalfDays);
		em.persist(F);
	}
	
	@Transactional
	public void addFormation(Training F){
		em.persist(F);
	}
	
	
	public boolean isFormationPersistent(String titreFormation){

		Collection<Training> list = null;
		CriteriaBuilder cb = em.getCriteriaBuilder();
		 
		  CriteriaQuery<Training> q = cb.createQuery(Training.class);
		  Root<Training> c = q.from(Training.class);
		  //ParameterExpression<String> p = cb.parameter(String.class);
		  q.select(c).where(cb.equal(c.get("titreformation"), titreFormation));
		  
		  list = (Collection<Training>) em.createQuery(q).getResultList();
		  
		return !list.isEmpty(); //return true if the list is not avoid
	}
	
	public List<Training> getAllFormation() {
		return em.createQuery("select a from Formation a", Training.class).getResultList();
	}
	
	/*** Session Training ***/
	
	public List<SessionFormation> getSessionByFormation(long myFormationId) {
		Query q=em.createQuery("select s from SessionFormation s where s.formation.id=:myFormationId").setParameter("myFormationId",myFormationId);
		return q.getResultList();
	}
	
	public List<SessionFormation> getAllSessionFormation() {
		return em.createQuery("select a from SessionFormation a", SessionFormation.class).getResultList();
	}
	
	@Transactional
	public void addSessionFormation(SessionFormation sf){
		em.persist(sf);
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
		  
		  q.select(c).where(cb.equal(c.get("formation"), sf.getTraining().getId()),
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
