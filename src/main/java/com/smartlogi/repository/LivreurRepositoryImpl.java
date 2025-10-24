package com.smartlogi.repository;

import com.smartlogi.model.Livreur;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class LivreurRepositoryImpl implements LivreurRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Livreur save(Livreur livreur) {
        if (livreur.getId() == null) {
            entityManager.persist(livreur);
            return livreur;
        } else {
            return entityManager.merge(livreur);
        }
    }

    @Override
    public Optional<Livreur> findById(Long id) {
        Livreur livreur = entityManager.find(Livreur.class, id);
        return Optional.ofNullable(livreur);
    }

    @Override
    public List<Livreur> findAll() {
        TypedQuery<Livreur> query = entityManager.createQuery(
                "SELECT l FROM Livreur l", Livreur.class);
        return query.getResultList();
    }

    @Override
    public void deleteById(Long id) {
        Livreur livreur = entityManager.find(Livreur.class, id);
        if (livreur != null) {
            entityManager.remove(livreur);
        }
    }

    @Override
    public boolean existsById(Long id) {
        TypedQuery<Long> query = entityManager.createQuery(
                "SELECT COUNT(l) FROM Livreur l WHERE l.id = :id", Long.class);
        query.setParameter("id", id);
        return query.getSingleResult() > 0;
    }

    @Override
    public Optional<Livreur> findByTelephone(String telephone) {
        TypedQuery<Livreur> query = entityManager.createQuery(
                "SELECT l FROM Livreur l WHERE l.telephone = :telephone", Livreur.class);
        query.setParameter("telephone", telephone);
        try {
            Livreur livreur = query.getSingleResult();
            return Optional.of(livreur);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}