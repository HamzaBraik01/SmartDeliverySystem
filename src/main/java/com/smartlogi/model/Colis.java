package com.smartlogi.model;


import javax.persistence.*;

@Entity
@Table(name = "colis")
public class Colis {

    public enum Statut {
        PREPARATION,
        EN_TRANSIT,
        LIVRE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "destinataire", nullable = false)
    private String destinataire;

    @Column(name = "adresse", nullable = false)
    private String adresse;

    @Column(name = "poids")
    private Double poids;

    @Enumerated(EnumType.STRING)
    @Column(name = "statut", nullable = false)
    private Statut statut;

    @Column(name = "id_livreur")
    private Long idLivreur;

    // Constructeurs
    public Colis() {
        this.statut = Statut.PREPARATION;
    }

    public Colis(String destinataire, String adresse, Double poids, Long idLivreur) {
        this.destinataire = destinataire;
        this.adresse = adresse;
        this.poids = poids;
        this.idLivreur = idLivreur;
        this.statut = Statut.PREPARATION;
    }

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDestinataire() { return destinataire; }
    public void setDestinataire(String destinataire) { this.destinataire = destinataire; }

    public String getAdresse() { return adresse; }
    public void setAdresse(String adresse) { this.adresse = adresse; }

    public Double getPoids() { return poids; }
    public void setPoids(Double poids) { this.poids = poids; }

    public Statut getStatut() { return statut; }
    public void setStatut(Statut statut) { this.statut = statut; }

    public Long getIdLivreur() { return idLivreur; }
    public void setIdLivreur(Long idLivreur) { this.idLivreur = idLivreur; }

    @Override
    public String toString() {
        return "Colis{" +
                "id=" + id +
                ", destinataire='" + destinataire + '\'' +
                ", adresse='" + adresse + '\'' +
                ", poids=" + poids +
                ", statut=" + statut +
                ", idLivreur=" + idLivreur +
                '}';
    }
}
