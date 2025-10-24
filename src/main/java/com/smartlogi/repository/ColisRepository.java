package com.smartlogi.repository;

import com.smartlogi.model.Colis;
import com.smartlogi.model.StatutColis;
import java.util.List;
import java.util.Optional;

public interface ColisRepository {


    Colis save(Colis colis);


    Optional<Colis> findById(Long id);


    List<Colis> findAll();


    void deleteById(Long id);


    boolean existsById(Long id);


    List<Colis> findByLivreurId(Long livreurId);


    List<Colis> findByStatut(StatutColis statut);


    List<Colis> findUnassignedColis();
}