package com.smartlogi.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "colis")
public class Colis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "destinataire", nullable = false, length = 100)
    private String destinataire;

    @Column(name = "adresse", nullable = false, length = 255)
    private String adresse;

    @Column(name = "poids", nullable = false, precision = 10, scale = 2)
    private BigDecimal poids;

    @Enumerated(EnumType.STRING)
    @Column(name = "statut", nullable = false)
    private StatutColis statut;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_livreur", referencedColumnName = "id")
    private Livreur livreur;

    // Constructeurs
    public Colis() {}

    public Colis(String destinataire, String adresse, BigDecimal poids, StatutColis statut) {
        this.destinataire = destinataire;
        this.adresse = adresse;
        this.poids = poids;
        this.statut = statut;
    }

    public Colis(String destinataire, String adresse, BigDecimal poids, StatutColis statut, Livreur livreur) {
        this.destinataire = destinataire;
        this.adresse = adresse;
        this.poids = poids;
        this.statut = statut;
        this.livreur = livreur;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDestinataire() {
        return destinataire;
    }

    public void setDestinataire(String destinataire) {
        this.destinataire = destinataire;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public BigDecimal getPoids() {
        return poids;
    }

    public void setPoids(BigDecimal poids) {
        this.poids = poids;
    }

    public StatutColis getStatut() {
        return statut;
    }

    public void setStatut(StatutColis statut) {
        this.statut = statut;
    }

    public Livreur getLivreur() {
        return livreur;
    }

    public void setLivreur(Livreur livreur) {
        this.livreur = livreur;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Colis colis = (Colis) o;
        return Objects.equals(id, colis.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Colis{" +
                "id=" + id +
                ", destinataire='" + destinataire + '\'' +
                ", adresse='" + adresse + '\'' +
                ", poids=" + poids +
                ", statut=" + statut +
                ", livreur=" + (livreur != null ? livreur.getNom() + " " + livreur.getPrenom() : "Non assigné") +
                '}';
    }
}