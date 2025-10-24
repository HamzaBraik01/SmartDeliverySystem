package com.smartlogi.service;

import com.smartlogi.model.Colis;
import com.smartlogi.model.StatutColis;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ColisService {


    Colis enregistrerColis(String destinataire, String adresse, BigDecimal poids);


    Optional<Colis> assignerColisALivreur(Long colisId, Long livreurId);


    Optional<Colis> mettreAJourStatut(Long colisId, StatutColis nouveauStatut);


    Optional<Colis> trouverParId(Long id);


    List<Colis> listerTousLesColis();


    List<Colis> listerColisParLivreur(Long livreurId);


    List<Colis> listerColisParStatut(StatutColis statut);


    List<Colis> listerColisNonAssignes();


    boolean supprimerColis(Long id);


    Optional<Colis> modifierColis(Long id, String destinataire, String adresse, BigDecimal poids);
}