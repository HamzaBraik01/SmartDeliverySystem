package com.smartlogi.service;

import com.smartlogi.model.Colis;
import com.smartlogi.model.Livreur;
import com.smartlogi.model.StatutColis;
import com.smartlogi.repository.ColisRepository;
import com.smartlogi.repository.LivreurRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ColisServiceImpl implements ColisService {

    private ColisRepository colisRepository;
    private LivreurRepository livreurRepository;

    // Constructor injection (configuré via XML)
    public ColisServiceImpl(LivreurRepository livreurRepository) {
        this.livreurRepository = livreurRepository;
    }

    // Setter injection (configuré via XML)
    public void setColisRepository(ColisRepository colisRepository) {
        this.colisRepository = colisRepository;
    }

    @Override
    public Colis enregistrerColis(String destinataire, String adresse, BigDecimal poids) {
        // Validation des données
        if (destinataire == null || destinataire.trim().isEmpty()) {
            throw new IllegalArgumentException("Le destinataire ne peut pas être vide");
        }
        if (adresse == null || adresse.trim().isEmpty()) {
            throw new IllegalArgumentException("L'adresse ne peut pas être vide");
        }
        if (poids == null || poids.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Le poids doit être positif");
        }

        Colis colis = new Colis(destinataire.trim(), adresse.trim(), poids, StatutColis.PREPARATION);
        return colisRepository.save(colis);
    }

    @Override
    public Optional<Colis> assignerColisALivreur(Long colisId, Long livreurId) {
        Optional<Colis> colisOpt = colisRepository.findById(colisId);
        Optional<Livreur> livreurOpt = livreurRepository.findById(livreurId);

        if (colisOpt.isPresent() && livreurOpt.isPresent()) {
            Colis colis = colisOpt.get();
            Livreur livreur = livreurOpt.get();

            colis.setLivreur(livreur);
            // Mettre automatiquement en transit si assigné
            if (colis.getStatut() == StatutColis.PREPARATION) {
                colis.setStatut(StatutColis.EN_TRANSIT);
            }

            return Optional.of(colisRepository.save(colis));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Colis> mettreAJourStatut(Long colisId, StatutColis nouveauStatut) {
        Optional<Colis> colisOpt = colisRepository.findById(colisId);
        if (colisOpt.isPresent()) {
            Colis colis = colisOpt.get();
            colis.setStatut(nouveauStatut);
            return Optional.of(colisRepository.save(colis));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Colis> trouverParId(Long id) {
        return colisRepository.findById(id);
    }

    @Override
    public List<Colis> listerTousLesColis() {
        return colisRepository.findAll();
    }

    @Override
    public List<Colis> listerColisParLivreur(Long livreurId) {
        return colisRepository.findByLivreurId(livreurId);
    }

    @Override
    public List<Colis> listerColisParStatut(StatutColis statut) {
        return colisRepository.findByStatut(statut);
    }

    @Override
    public List<Colis> listerColisNonAssignes() {
        return colisRepository.findUnassignedColis();
    }

    @Override
    public boolean supprimerColis(Long id) {
        if (colisRepository.existsById(id)) {
            colisRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Optional<Colis> modifierColis(Long id, String destinataire, String adresse, BigDecimal poids) {
        Optional<Colis> colisOpt = colisRepository.findById(id);
        if (colisOpt.isPresent()) {
            Colis colis = colisOpt.get();

            // Validation des données
            if (destinataire != null && !destinataire.trim().isEmpty()) {
                colis.setDestinataire(destinataire.trim());
            }
            if (adresse != null && !adresse.trim().isEmpty()) {
                colis.setAdresse(adresse.trim());
            }
            if (poids != null && poids.compareTo(BigDecimal.ZERO) > 0) {
                colis.setPoids(poids);
            }

            return Optional.of(colisRepository.save(colis));
        }
        return Optional.empty();
    }
}