package com.viseo.c360.formation.dao;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.viseo.c360.formation.domain.collaborateur.Collaborateur;

@Repository
public class CollaborateurDAO {
	
	@PersistenceContext
	EntityManager em;
	
	@Transactional
	public void addCollaborateur(Collaborateur collaborateur){
		em.persist(collaborateur);
	}

	public boolean isMatriculePersistent(String matricule){
		em.setFlushMode(FlushModeType.COMMIT);
		Collection<Collaborateur> listCollaborateur =
				(Collection<Collaborateur>) em.createQuery(
						"select c from Collaborateur c where c.matricule = :matricule", Collaborateur.class)
						.setParameter("matricule",matricule).getResultList();
		return !listCollaborateur.isEmpty();
	}
	
	public List<Collaborateur> getAllCollaborateur() {
		return em.createQuery("select c from Collaborateur c", Collaborateur.class).getResultList();
	}
	
}
