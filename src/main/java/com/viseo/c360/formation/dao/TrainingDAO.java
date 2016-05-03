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
public class TrainingDAO {
	
	@PersistenceContext
	EntityManager em;

	/*** Training ***/
	public Training getTraining(long id){
		return em.find(Training.class, id);
	}

	@Transactional
	public void addTraining(String titleTraining, int numberHalfDays){
		Training training = new Training();
		training.setTitleTraining(titleTraining);
		training.setNumberHalfDays(numberHalfDays);
		em.persist(training);
	}
	
	@Transactional
	public void addTraining(Training training){
		em.persist(training);
	}
	
	public boolean isTrainingPersisted(String titleTraining){
		Collection<Training> listTrainings =
				(Collection<Training>) em.createQuery("select t from Training t where t.titleTraining = :titleTraining")
				.setParameter("titleTraining", titleTraining).getResultList();
		return !listTrainings.isEmpty();
	}
	
	public List<Training> getAllTrainings() {
		return em.createQuery("select a from Training a", Training.class).getResultList();
	}
	
	/*** Session Training ***/
	public List<TrainingSession> getSessionByTraining(long myTrainingId) {
		Query q = em.createQuery("select s from SessionTraining s where s.training.id=:myTrainingId")
				.setParameter("myTrainingId",myTrainingId);
		return q.getResultList();
	}
	
	public List<TrainingSession> getAllSessionsTrainings() {
		return em.createQuery("select s from SessionTraining s", TrainingSession.class).getResultList();
	}
	
	@Transactional
	public void addSessionTraining(TrainingSession trainingSession){
		em.persist(trainingSession);
	}

	public TrainingSession getSessionTraining(long id) {
		return em.find(TrainingSession.class, id);
	}
	
	public boolean isThereOneSessionTrainingAlreadyPlanned(TrainingSession trainingSession){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		  CriteriaQuery<TrainingSession> q = cb.createQuery(TrainingSession.class);
		  Root<TrainingSession> c = q.from(TrainingSession.class);
		  q.select(c).where(cb.equal(c.get("formation"), trainingSession.getTraining().getId()),
				  cb.or(
					  cb.and(
						  cb.greaterThanOrEqualTo(c.<Date>get("debut"), trainingSession.getBeginning()),
						  cb.lessThan(c.<Date>get("debut"), trainingSession.getEnding())
						  ),
					  cb.and(
							  cb.greaterThan(c.<Date>get("fin"), trainingSession.getBeginning()),
							  cb.lessThanOrEqualTo(c.<Date>get("fin"), trainingSession.getEnding())
						  ),
					  cb.and(
							  cb.lessThanOrEqualTo(c.<Date>get("debut"), trainingSession.getBeginning()),
							  cb.greaterThanOrEqualTo(c.<Date>get("fin"), trainingSession.getEnding())
						  )
					  )
		  );
		Collection<TrainingSession> list = (Collection<TrainingSession>) em.createQuery(q).getResultList();
		return !list.isEmpty();
	}
	
	public boolean hasCorrectDates(TrainingSession myTrainingSession){
		return myTrainingSession.getBeginning().before(myTrainingSession.getEnding());
	}
}
