package com.smartlogi.service;

import com.smartlogi.model.Livreur;
import com.smartlogi.repository.LivreurRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LivreurServiceImpl implements LivreurService {

    private LivreurRepository livreurRepository;

    // Constructor injection (configuré via XML)
    public LivreurServiceImpl(LivreurRepository livreurRepository) {
        this.livreurRepository = livreurRepository;
    }

    // Setter injection (configuré via XML - optionnel car déjà injecté par constructeur)
    public void setLivreurRepository(LivreurRepository livreurRepository) {
        this.livreurRepository = livreurRepository;
    }

    @Override
    public Livreur creerLivreur(String nom, String prenom, String vehicule, String telephone) {
        // Validation des données
        if (nom == null || nom.trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom ne peut pas être vide");
        }
        if (prenom == null || prenom.trim().isEmpty()) {
            throw new IllegalArgumentException("Le prénom ne peut pas être vide");
        }
        if (vehicule == null || vehicule.trim().isEmpty()) {
            throw new IllegalArgumentException("Le véhicule ne peut pas être vide");
        }
        if (telephone == null || telephone.trim().isEmpty()) {
            throw new IllegalArgumentException("Le téléphone ne peut pas être vide");
        }

        // Vérifier si le téléphone existe déjà
        if (telephoneExiste(telephone)) {
            throw new IllegalArgumentException("Ce numéro de téléphone est déjà utilisé");
        }

        Livreur livreur = new Livreur(nom.trim(), prenom.trim(), vehicule.trim(), telephone.trim());
        return livreurRepository.save(livreur);
    }

    @Override
    public Optional<Livreur> modifierLivreur(Long id, String nom, String prenom, String vehicule, String telephone) {
        Optional<Livreur> livreurOpt = livreurRepository.findById(id);
        if (livreurOpt.isPresent()) {
            Livreur livreur = livreurOpt.get();

            // Vérifier si le nouveau téléphone existe déjà (sauf si c'est le même livreur)
            if (!livreur.getTelephone().equals(telephone) && telephoneExiste(telephone)) {
                throw new IllegalArgumentException("Ce numéro de téléphone est déjà utilisé");
            }

            livreur.setNom(nom.trim());
            livreur.setPrenom(prenom.trim());
            livreur.setVehicule(vehicule.trim());
            livreur.setTelephone(telephone.trim());

            return Optional.of(livreurRepository.save(livreur));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Livreur> trouverParId(Long id) {
        return livreurRepository.findById(id);
    }

    @Override
    public List<Livreur> listerTousLesLivreurs() {
        return livreurRepository.findAll();
    }

    @Override
    public boolean supprimerLivreur(Long id) {
        if (livreurRepository.existsById(id)) {
            livreurRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean telephoneExiste(String telephone) {
        return livreurRepository.findByTelephone(telephone).isPresent();
    }

    @Override
    public Optional<Livreur> trouverParTelephone(String telephone) {
        return livreurRepository.findByTelephone(telephone);
    }
}