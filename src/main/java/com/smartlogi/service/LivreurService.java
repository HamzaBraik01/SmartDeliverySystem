package com.smartlogi.service;

import com.smartlogi.model.Livreur;
import java.util.List;
import java.util.Optional;

public interface LivreurService {


    Livreur creerLivreur(String nom, String prenom, String vehicule, String telephone);


    Optional<Livreur> modifierLivreur(Long id, String nom, String prenom, String vehicule, String telephone);


    Optional<Livreur> trouverParId(Long id);


    List<Livreur> listerTousLesLivreurs();


    boolean supprimerLivreur(Long id);


    boolean telephoneExiste(String telephone);


    Optional<Livreur> trouverParTelephone(String telephone);
}