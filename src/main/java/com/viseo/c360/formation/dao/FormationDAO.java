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

import com.viseo.c360.formation.domain.training.Training;
import com.viseo.c360.formation.domain.training.TrainingSession;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
	
	public List<TrainingSession> getSessionByFormation(long myFormationId) {
		Query q=em.createQuery("select s from SessionFormation s where s.formation.id=:myFormationId").setParameter("myFormationId",myFormationId);
		return q.getResultList();
	}
	
	public List<TrainingSession> getAllSessionFormation() {
		return em.createQuery("select a from SessionFormation a", TrainingSession.class).getResultList();
	}
	
	@Transactional
	public void addSessionFormation(TrainingSession sf){
		em.persist(sf);
	}
	
	@Transactional
	public TrainingSession getSessionFormation(long id) {
		return em.find(TrainingSession.class, id);
	}
	
	public boolean isThereOneSessionFormationAlreadyPlanned(TrainingSession sf){

		Collection<TrainingSession> list = null;
		CriteriaBuilder cb = em.getCriteriaBuilder();
		 
		  CriteriaQuery<TrainingSession> q = cb.createQuery(TrainingSession.class);
		  Root<TrainingSession> c = q.from(TrainingSession.class);
		  //ParameterExpression<String> p = cb.parameter(String.class);
		  
		  q.select(c).where(cb.equal(c.get("formation"), sf.getTraining().getId()),
				  cb.or(
					  cb.and(
						  cb.greaterThanOrEqualTo(c.<Date>get("debut"), sf.getBeginning()),
						  cb.lessThan(c.<Date>get("debut"), sf.getEnding())
						  ),
					  cb.and(
							  cb.greaterThan(c.<Date>get("fin"), sf.getBeginning()),
							  cb.lessThanOrEqualTo(c.<Date>get("fin"), sf.getEnding())
						  ),
					  cb.and(
							  cb.lessThanOrEqualTo(c.<Date>get("debut"), sf.getBeginning()),
							  cb.greaterThanOrEqualTo(c.<Date>get("fin"), sf.getEnding())
						  )
					  )
		  );
		  
		  list = (Collection<TrainingSession>) em.createQuery(q).getResultList();
		  
		return !list.isEmpty();
	}
	
	public boolean hasCorrectDates(TrainingSession myTrainingSession){
		return myTrainingSession.getBeginning().before(myTrainingSession.getEnding());
	}
}
