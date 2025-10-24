package com.smartlogi.repository;

import com.smartlogi.model.Livreur;
import java.util.List;
import java.util.Optional;

public interface LivreurRepository {


    Livreur save(Livreur livreur);


    Optional<Livreur> findById(Long id);


    List<Livreur> findAll();


    void deleteById(Long id);


    boolean existsById(Long id);


    Optional<Livreur> findByTelephone(String telephone);
}