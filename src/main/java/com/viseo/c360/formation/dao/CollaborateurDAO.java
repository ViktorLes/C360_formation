package com.viseo.c360.formation.dao;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.viseo.c360.formation.domain.collaborateur.Collaborator;

@Repository
public class CollaborateurDAO {
	
	@PersistenceContext
	EntityManager em;
	
	@Transactional
	public void addCollaborateur(Collaborator collaborator){
		em.persist(collaborator);
	}

	public boolean isMatriculePersistent(String matricule){
		em.setFlushMode(FlushModeType.COMMIT);
		Collection<Collaborator> listCollaborator =
				(Collection<Collaborator>) em.createQuery(
						"select c from Collaborateur c where c.matricule = :matricule", Collaborator.class)
						.setParameter("matricule",matricule).getResultList();
		return !listCollaborator.isEmpty();
	}
	
	public List<Collaborator> getAllCollaborateur() {
		return em.createQuery("select c from Collaborateur c", Collaborator.class).getResultList();
	}
	
}
