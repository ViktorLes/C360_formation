package com.viseo.c360.formation.dao;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.viseo.c360.formation.domain.collaborator.Collaborator;

@Repository
public class CollaboratorDAO {
	
	@PersistenceContext
	EntityManager em;
	
	@Transactional
	public void addCollaborator(Collaborator collaborator){
		em.persist(collaborator);
	}

	public boolean isPersonnalIdNumberPersisted(String personnalIdNumber){
		em.setFlushMode(FlushModeType.COMMIT);
		Collection<Collaborator> listCollaborator =
				(Collection<Collaborator>) em.createQuery(
						"select c from Collaborator c where c.personnalIdNumber = :personnalIdNumber", Collaborator.class)
						.setParameter("personnalIdNumber",personnalIdNumber).getResultList();
		return !listCollaborator.isEmpty();
	}
	
	public List<Collaborator> getAllCollaborators() {
		em.setFlushMode(FlushModeType.COMMIT);
		return em.createQuery("select c from Collaborator c", Collaborator.class).getResultList();
	}
	
}
