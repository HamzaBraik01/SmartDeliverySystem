package com.smartlogi.repository;

import com.smartlogi.model.Colis;
import com.smartlogi.model.StatutColis;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class ColisRepositoryImpl implements ColisRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Colis save(Colis colis) {
        if (colis.getId() == null) {
            entityManager.persist(colis);
            return colis;
        } else {
            return entityManager.merge(colis);
        }
    }

    @Override
    public Optional<Colis> findById(Long id) {
        Colis colis = entityManager.find(Colis.class, id);
        return Optional.ofNullable(colis);
    }

    @Override
    public List<Colis> findAll() {
        TypedQuery<Colis> query = entityManager.createQuery(
                "SELECT c FROM Colis c LEFT JOIN FETCH c.livreur", Colis.class);
        return query.getResultList();
    }

    @Override
    public void deleteById(Long id) {
        Colis colis = entityManager.find(Colis.class, id);
        if (colis != null) {
            entityManager.remove(colis);
        }
    }

    @Override
    public boolean existsById(Long id) {
        TypedQuery<Long> query = entityManager.createQuery(
                "SELECT COUNT(c) FROM Colis c WHERE c.id = :id", Long.class);
        query.setParameter("id", id);
        return query.getSingleResult() > 0;
    }

    @Override
    public List<Colis> findByLivreurId(Long livreurId) {
        TypedQuery<Colis> query = entityManager.createQuery(
                "SELECT c FROM Colis c LEFT JOIN FETCH c.livreur WHERE c.livreur.id = :livreurId",
                Colis.class);
        query.setParameter("livreurId", livreurId);
        return query.getResultList();
    }

    @Override
    public List<Colis> findByStatut(StatutColis statut) {
        TypedQuery<Colis> query = entityManager.createQuery(
                "SELECT c FROM Colis c LEFT JOIN FETCH c.livreur WHERE c.statut = :statut",
                Colis.class);
        query.setParameter("statut", statut);
        return query.getResultList();
    }

    @Override
    public List<Colis> findUnassignedColis() {
        TypedQuery<Colis> query = entityManager.createQuery(
                "SELECT c FROM Colis c WHERE c.livreur IS NULL", Colis.class);
        return query.getResultList();
    }
}