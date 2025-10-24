package com.smartlogi;

import com.smartlogi.model.Colis;
import com.smartlogi.model.Livreur;
import com.smartlogi.model.StatutColis;
import com.smartlogi.service.ColisService;
import com.smartlogi.service.LivreurService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== SMART DELIVERY MANAGEMENT SYSTEM ===");
        System.out.println("Démarrage de l'application...");

        try {
            ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

            LivreurService livreurService = context.getBean("livreurService", LivreurService.class);
            ColisService colisService = context.getBean("colisService", ColisService.class);

            System.out.println("Contexte Spring initialisé avec succès!");
            System.out.println();

            testLivreurCRUD(livreurService);

            testColisCRUD(colisService, livreurService);

            testAssignationColis(colisService, livreurService);

            testSuiviStatuts(colisService);

            System.out.println("\n=== TESTS TERMINÉS AVEC SUCCÈS ===");

        } catch (Exception e) {
            System.err.println("Erreur lors de l'exécution: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void testLivreurCRUD(LivreurService livreurService) {
        System.out.println("=== TEST CRUD LIVREURS ===");

        try {
            System.out.println("1. Création de livreurs...");
            Livreur livreur1 = livreurService.creerLivreur("Dupont", "Jean", "Camionnette", "0123456789");
            Livreur livreur2 = livreurService.creerLivreur("Martin", "Marie", "Scooter", "0987654321");
            Livreur livreur3 = livreurService.creerLivreur("Bernard", "Paul", "Vélo", "0555123456");

            System.out.println("✓ Livreur créé: " + livreur1);
            System.out.println("✓ Livreur créé: " + livreur2);
            System.out.println("✓ Livreur créé: " + livreur3);

            System.out.println("\n2. Liste de tous les livreurs:");
            List<Livreur> livreurs = livreurService.listerTousLesLivreurs();
            livreurs.forEach(l -> System.out.println("  " + l));

            System.out.println("\n3. Recherche par ID:");
            Optional<Livreur> livreurTrouve = livreurService.trouverParId(livreur1.getId());
            if (livreurTrouve.isPresent()) {
                System.out.println("✓ Livreur trouvé: " + livreurTrouve.get());
            }

            System.out.println("\n4. Modification d'un livreur:");
            Optional<Livreur> livreurModifie = livreurService.modifierLivreur(
                livreur2.getId(), "Martin", "Marie-Claire", "Voiture", "0987654321");
            if (livreurModifie.isPresent()) {
                System.out.println("✓ Livreur modifié: " + livreurModifie.get());
            }

        } catch (Exception e) {
            System.err.println("Erreur lors du test CRUD livreurs: " + e.getMessage());
        }

        System.out.println();
    }

    private static void testColisCRUD(ColisService colisService, LivreurService livreurService) {
        System.out.println("=== TEST CRUD COLIS ===");

        try {
            // Création de colis
            System.out.println("1. Enregistrement de colis...");
            Colis colis1 = colisService.enregistrerColis("Alice Durand", "123 Rue de la Paix, Paris", new BigDecimal("2.5"));
            Colis colis2 = colisService.enregistrerColis("Bob Smith", "456 Avenue des Champs, Lyon", new BigDecimal("1.2"));
            Colis colis3 = colisService.enregistrerColis("Claire Moreau", "789 Boulevard Victor Hugo, Marseille", new BigDecimal("3.8"));
            Colis colis4 = colisService.enregistrerColis("David Brown", "321 Rue de Rivoli, Toulouse", new BigDecimal("0.8"));

            System.out.println("✓ Colis enregistré: " + colis1);
            System.out.println("✓ Colis enregistré: " + colis2);
            System.out.println("✓ Colis enregistré: " + colis3);
            System.out.println("✓ Colis enregistré: " + colis4);

            System.out.println("\n2. Liste de tous les colis:");
            List<Colis> colis = colisService.listerTousLesColis();
            colis.forEach(c -> System.out.println("  " + c));

            System.out.println("\n3. Modification d'un colis:");
            Optional<Colis> colisModifie = colisService.modifierColis(
                colis1.getId(), "Alice Durand-Martin", "123 Rue de la Paix, Paris 75001", new BigDecimal("2.7"));
            if (colisModifie.isPresent()) {
                System.out.println("✓ Colis modifié: " + colisModifie.get());
            }

        } catch (Exception e) {
            System.err.println("Erreur lors du test CRUD colis: " + e.getMessage());
        }

        System.out.println();
    }

    private static void testAssignationColis(ColisService colisService, LivreurService livreurService) {
        System.out.println("=== TEST ASSIGNATION COLIS AUX LIVREURS ===");

        try {
            List<Livreur> livreurs = livreurService.listerTousLesLivreurs();
            List<Colis> colis = colisService.listerTousLesColis();

            if (!livreurs.isEmpty() && !colis.isEmpty()) {
                System.out.println("1. Assignation des colis...");

                if (colis.size() > 0) {
                    Optional<Colis> colisAssigne1 = colisService.assignerColisALivreur(colis.get(0).getId(), livreurs.get(0).getId());
                    if (colisAssigne1.isPresent()) {
                        System.out.println("✓ Colis assigné à " + livreurs.get(0).getNom() + ": " + colisAssigne1.get());
                    }
                }

                if (colis.size() > 1 && livreurs.size() > 1) {
                    Optional<Colis> colisAssigne2 = colisService.assignerColisALivreur(colis.get(1).getId(), livreurs.get(1).getId());
                    if (colisAssigne2.isPresent()) {
                        System.out.println("✓ Colis assigné à " + livreurs.get(1).getNom() + ": " + colisAssigne2.get());
                    }
                }

                if (colis.size() > 2) {
                    Optional<Colis> colisAssigne3 = colisService.assignerColisALivreur(colis.get(2).getId(), livreurs.get(0).getId());
                    if (colisAssigne3.isPresent()) {
                        System.out.println("✓ Colis assigné à " + livreurs.get(0).getNom() + ": " + colisAssigne3.get());
                    }
                }

                System.out.println("\n2. Colis par livreur:");
                for (Livreur livreur : livreurs) {
                    List<Colis> colisLivreur = colisService.listerColisParLivreur(livreur.getId());
                    System.out.println("  " + livreur.getNom() + " " + livreur.getPrenom() + " (" + colisLivreur.size() + " colis):");
                    colisLivreur.forEach(c -> System.out.println("    - " + c));
                }

                System.out.println("\n3. Colis non assignés:");
                List<Colis> colisNonAssignes = colisService.listerColisNonAssignes();
                if (colisNonAssignes.isEmpty()) {
                    System.out.println("  Aucun colis non assigné.");
                } else {
                    colisNonAssignes.forEach(c -> System.out.println("  " + c));
                }
            }

        } catch (Exception e) {
            System.err.println("Erreur lors du test d'assignation: " + e.getMessage());
        }

        System.out.println();
    }

    private static void testSuiviStatuts(ColisService colisService) {
        System.out.println("=== TEST SUIVI DES STATUTS ===");

        try {
            List<Colis> colis = colisService.listerTousLesColis();

            if (!colis.isEmpty()) {
                // Mettre à jour le statut de quelques colis
                System.out.println("1. Mise à jour des statuts...");

                if (colis.size() > 0) {
                    Optional<Colis> colisLivre = colisService.mettreAJourStatut(colis.get(0).getId(), StatutColis.LIVRE);
                    if (colisLivre.isPresent()) {
                        System.out.println("✓ Statut mis à jour: " + colisLivre.get());
                    }
                }

                if (colis.size() > 1) {
                    Optional<Colis> colisTransit = colisService.mettreAJourStatut(colis.get(1).getId(), StatutColis.EN_TRANSIT);
                    if (colisTransit.isPresent()) {
                        System.out.println("✓ Statut mis à jour: " + colisTransit.get());
                    }
                }

                // Lister par statut
                System.out.println("\n2. Colis par statut:");
                for (StatutColis statut : StatutColis.values()) {
                    List<Colis> colisParStatut = colisService.listerColisParStatut(statut);
                    System.out.println("  " + statut.getDescription() + " (" + colisParStatut.size() + " colis):");
                    colisParStatut.forEach(c -> System.out.println("    - " + c));
                }
            }

        } catch (Exception e) {
            System.err.println("Erreur lors du test de suivi des statuts: " + e.getMessage());
        }

        System.out.println();
    }
}