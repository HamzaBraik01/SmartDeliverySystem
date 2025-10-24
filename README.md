# 🚚 Smart Delivery Management System

![Java](https://img.shields.io/badge/Java-17-orange?style=flat-square&logo=java)
![Spring](https://img.shields.io/badge/Spring-5.3.27-green?style=flat-square&logo=spring)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-blue?style=flat-square&logo=postgresql)
![Maven](https://img.shields.io/badge/Maven-3.8+-red?style=flat-square&logo=apache-maven)

## 📋 Table des Matières

- [Contexte du Projet](#-contexte-du-projet)
- [Problématique](#-problématique)
- [Solution Proposée](#-solution-proposée)
- [User Stories](#-user-stories)
- [Architecture](#-architecture)
- [Technologies Utilisées](#-technologies-utilisées)
- [Modèle de Données](#-modèle-de-données)
- [Installation](#-installation)
- [Configuration](#-configuration)
- [Utilisation](#-utilisation)
- [Fonctionnalités](#-fonctionnalités)
- [Structure du Projet](#-structure-du-projet)
- [Évolutions Futures](#-évolutions-futures)

---

## 🎯 Contexte du Projet

La société **SmartLogi** souhaite moderniser et automatiser la gestion de ses livraisons afin d'améliorer la **précision**, la **fiabilité** et l'**efficacité** de ses opérations logistiques.

Actuellement, le suivi des colis et des livreurs se fait **manuellement** à l'aide de fichiers Excel et de registres papier.

---

## ❌ Problématique

Cette méthode manuelle entraîne plusieurs problèmes concrets :

### 1. **Erreurs de Saisie**
- Colis enregistrés avec des informations incorrectes (adresse, poids, destinataire)
- Données incohérentes et non fiables

### 2. **Retards dans les Livraisons**
- Informations dispersées rendant difficile la planification des tournées
- Absence de suivi en temps réel

### 3. **Double Enregistrement ou Perte de Données**
- Certains colis enregistrés plusieurs fois
- Colis non suivis correctement

### 4. **Visibilité Limitée**
- Difficulté à obtenir des rapports précis sur l'état des livraisons
- Charge de travail des livreurs mal évaluée

---

## ✅ Solution Proposée

Le **Smart Delivery Management System** est un système centralisé permettant de :

- ✅ Gérer efficacement les informations sur les colis et les livreurs
- ✅ Éviter les erreurs de saisie et les doublons
- ✅ Améliorer la planification et la visibilité des livraisons
- ✅ Automatiser le suivi en temps réel
- ✅ Générer des rapports fiables

---

## 📖 User Stories

### 🔹 US1 : Gérer les Livreurs (CRUD)
**En tant que** gestionnaire logistique  
**Je veux** gérer les livreurs (créer, lire, modifier, supprimer)  
**Afin que** toutes les informations soient centralisées et fiables, tout en évitant les doublons et les erreurs de saisie

### 🔹 US2 : Enregistrer un Colis
**En tant que** gestionnaire logistique  
**Je veux** enregistrer un colis et l'assigner à un livreur  
**Afin de** suivre précisément chaque livraison et éviter les pertes ou enregistrements multiples

### 🔹 US3 : Mettre à Jour le Statut d'un Colis
**En tant que** gestionnaire logistique  
**Je veux** mettre à jour le statut d'un colis (préparation, en transit, livré)  
**Afin d'** assurer une visibilité en temps réel sur l'avancement des livraisons

### 🔹 US4 : Lister les Colis par Livreur
**En tant que** gestionnaire logistique  
**Je veux** lister tous les colis assignés à un livreur  
**Afin de** planifier efficacement les tournées et réduire les retards

### 🔹 US5 : Corriger les Données
**En tant que** gestionnaire logistique  
**Je veux** supprimer ou corriger une information erronée  
**Afin de** garantir la fiabilité et l'intégrité des données

---

## 🏗️ Architecture

### Diagramme de Classes

![Diagramme de Classes](Diagramme/Diagramme%20class.png)

### Architecture en Couches

```
┌─────────────────────────────────────┐
│         Main.java (Tests)           │
│    (Point d'entrée du système)      │
└─────────────────────────────────────┘
                ↓
┌─────────────────────────────────────┐
│      Couche Service (Métier)        │
│  - LivreurServiceImpl               │
│  - ColisServiceImpl                 │
└─────────────────────────────────────┘
                ↓
┌─────────────────────────────────────┐
│   Couche Repository (DAO/Accès)     │
│  - LivreurRepositoryImpl            │
│  - ColisRepositoryImpl              │
└─────────────────────────────────────┘
                ↓
┌─────────────────────────────────────┐
│      JPA/Hibernate (ORM)            │
│    EntityManager + Transactions     │
└─────────────────────────────────────┘
                ↓
┌─────────────────────────────────────┐
│      PostgreSQL Database            │
│    (smart_delivery_db)              │
└─────────────────────────────────────┘
```

---

## 🛠️ Technologies Utilisées

### Backend
- **Java 17** - Langage de programmation
- **Spring Core 5.3.27** - IoC, DI, Beans, Scopes
- **Spring Data JPA** - Persistance des entités
- **Hibernate 5.6.15** - ORM (Object-Relational Mapping)

### Base de Données
- **PostgreSQL 15** - SGBD relationnel
- **HikariCP** - Connection pooling

### Outils de Build
- **Maven 3.8+** - Gestion de projet et dépendances

### Versioning
- **Git / GitHub** - Contrôle de version

---

## 📊 Modèle de Données

### Entité : Livreur

| Champ      | Type          | Description                    |
|------------|---------------|--------------------------------|
| `id`       | Long          | Identifiant unique (PK)        |
| `nom`      | String        | Nom du livreur                 |
| `prenom`   | String        | Prénom du livreur              |
| `vehicule` | String        | Type de véhicule               |
| `telephone`| String        | Numéro de téléphone (unique)   |
| `colis`    | List<Colis>   | Liste des colis assignés       |

### Entité : Colis

| Champ          | Type         | Description                          |
|----------------|--------------|--------------------------------------|
| `id`           | Long         | Identifiant unique (PK)              |
| `destinataire` | String       | Nom du destinataire                  |
| `adresse`      | String       | Adresse de livraison                 |
| `poids`        | BigDecimal   | Poids du colis (kg)                  |
| `statut`       | StatutColis  | Statut (PREPARATION, EN_TRANSIT, LIVRE) |
| `livreur`      | Livreur      | Livreur assigné (FK)                 |

### Enum : StatutColis

```java
public enum StatutColis {
    PREPARATION("En préparation"),
    EN_TRANSIT("En transit"),
    LIVRE("Livré");
}
```

### Relations

- **Livreur** `1` ←→ `N` **Colis** (OneToMany / ManyToOne)

---

## 📥 Installation

### Prérequis

- ☑️ **Java 17** ou supérieur
- ☑️ **Maven 3.8+**
- ☑️ **PostgreSQL 15+**
- ☑️ **Git**

### Étapes d'Installation

#### 1. Cloner le Repository

```bash
git clone https://github.com/votre-username/smart-delivery-system.git
cd smart-delivery-system
```

#### 2. Créer la Base de Données

**Via psql :**
```bash
psql -U postgres
CREATE DATABASE smart_delivery_db;
\q
```

**Via pgAdmin :**
1. Ouvrir pgAdmin
2. Clic droit sur "Databases" → Create → Database
3. Nom : `smart_delivery_db`
4. Save

#### 3. Configurer la Base de Données

Modifier le fichier `src/main/resources/application.properties` :

```properties
db.driver=org.postgresql.Driver
db.url=jdbc:postgresql://localhost:5432/smart_delivery_db
db.username=postgres
db.password=VOTRE_MOT_DE_PASSE
```

#### 4. Compiler le Projet

```bash
mvn clean compile
```

#### 5. Créer le Package JAR

```bash
mvn clean package
```

---

## ⚙️ Configuration

### Configuration Spring (XML)

Le projet utilise la **configuration XML** de Spring pour l'injection de dépendances.

Fichier : `src/main/resources/applicationContext.xml`

#### Types d'Injection de Dépendances Utilisés

##### 1️⃣ Injection par Constructeur

```xml
<bean id="livreurService" class="com.smartlogi.service.LivreurServiceImpl">
    <constructor-arg ref="livreurRepository"/>
</bean>
```

##### 2️⃣ Injection par Setter

```xml
<bean id="colisService" class="com.smartlogi.service.ColisServiceImpl">
    <property name="colisRepository" ref="colisRepository"/>
</bean>
```

##### 3️⃣ Injection par Champ (@PersistenceContext)

```java
@PersistenceContext
private EntityManager entityManager;
```

### Scopes des Beans

```xml
<!-- Singleton : une seule instance partagée -->
<bean id="livreurRepository" scope="singleton"/>

<!-- Prototype : nouvelle instance à chaque demande -->
<bean id="colisRepository" scope="prototype"/>
```

---

## 🚀 Utilisation

### Exécuter l'Application

```bash
mvn exec:java
```

### Résultat Attendu

```
=== SMART DELIVERY MANAGEMENT SYSTEM ===
Démarrage de l'application...
Contexte Spring initialisé avec succès!

=== TEST CRUD LIVREURS ===
1. Création de livreurs...
✓ Livreur créé: Livreur{id=1, nom='Dupont', prenom='Jean', vehicule='Camionnette'}
✓ Livreur créé: Livreur{id=2, nom='Martin', prenom='Marie', vehicule='Scooter'}
✓ Livreur créé: Livreur{id=3, nom='Bernard', prenom='Paul', vehicule='Vélo'}

2. Liste de tous les livreurs:
  Livreur{id=1, nom='Dupont', prenom='Jean', vehicule='Camionnette'}
  ...

=== TEST CRUD COLIS ===
1. Enregistrement de colis...
✓ Colis enregistré: Colis{id=1, destinataire='Alice Durand', statut=PREPARATION}
...

=== TEST ASSIGNATION COLIS AUX LIVREURS ===
1. Assignation des colis...
✓ Colis assigné à Dupont: Colis{id=1, livreur=Livreur{nom='Dupont'}}
...

=== TEST SUIVI DES STATUTS ===
1. Mise à jour des statuts...
✓ Statut mis à jour: Colis{id=1, statut=LIVRE}
...

=== TESTS TERMINÉS AVEC SUCCÈS ===
```

---

## 🎯 Fonctionnalités

### Gestion des Livreurs

- ✅ **Créer** un livreur
- ✅ **Lire** les informations d'un livreur
- ✅ **Modifier** un livreur
- ✅ **Supprimer** un livreur
- ✅ **Lister** tous les livreurs
- �� **Rechercher** un livreur par téléphone (éviter les doublons)

### Gestion des Colis

- ✅ **Enregistrer** un colis
- ✅ **Modifier** les informations d'un colis
- ✅ **Supprimer** un colis
- ✅ **Assigner** un colis à un livreur
- ✅ **Mettre à jour** le statut d'un colis
- ✅ **Lister** tous les colis
- ✅ **Lister** les colis par livreur
- ✅ **Lister** les colis par statut
- ✅ **Lister** les colis non assignés

### Validation des Données

- ✅ Validation des champs obligatoires
- ✅ Vérification des doublons (téléphone)
- ✅ Validation du poids (doit être positif)
- ✅ Gestion des erreurs métier

---

## 📁 Structure du Projet

```
smart-delivery-system/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── smartlogi/
│   │   │           ├── Main.java                      # Point d'entrée
│   │   │           ├── model/
│   │   │           │   ├── Colis.java                 # Entité Colis
│   │   │           │   ├── Livreur.java               # Entité Livreur
│   │   │           │   └── StatutColis.java           # Enum Statut
│   │   │           ├── repository/
│   │   │           │   ├── ColisRepository.java       # Interface Repository
│   │   │           │   ├── ColisRepositoryImpl.java   # Implémentation JPA
│   │   │           │   ├── LivreurRepository.java
│   │   │           │   └── LivreurRepositoryImpl.java
│   │   │           └── service/
│   │   │               ├── ColisService.java          # Interface Service
│   │   │               ├── ColisServiceImpl.java      # Logique métier
│   │   │               ├── LivreurService.java
│   │   │               └── LivreurServiceImpl.java
│   │   └── resources/
│   │       ├── application.properties                 # Configuration DB
│   │       ├── applicationContext.xml                 # Configuration Spring
│   │       └── META-INF/
│   │           └── persistence.xml                    # Configuration JPA
│   └── test/
│       └── java/
│
├── Diagramme/
│   └── Diagramme class.png                           # Diagramme de classes
│
├── target/                                           # Fichiers compilés
│
├── pom.xml                                           # Configuration Maven
├── README.md                                         # Ce fichier
├── .gitignore                                        # Fichiers ignorés par Git
│
└── Documentation/
    ├── CONFIGURATION_XML_EXPLICATION.md              # Guide XML Spring
    ├── FLUX_VISUALISATION.md                         # Schémas d'architecture
    ├── MODIFICATIONS_RESUME.md                       # Historique des changements
    ├── README_CONFIGURATION_XML.md                   # Guide rapide XML
    └── CREATION_DATABASE.md                          # Guide création DB
```

---


## 🧪 Tests

### Tests Intégrés dans Main.java

Le projet inclut 4 suites de tests automatiques :

#### 1. Test CRUD Livreurs
```java
testLivreurCRUD(livreurService)
```
- Création de 3 livreurs
- Lecture de tous les livreurs
- Recherche par ID
- Modification d'un livreur

#### 2. Test CRUD Colis
```java
testColisCRUD(colisService, livreurService)
```
- Enregistrement de 4 colis
- Lecture de tous les colis
- Modification d'un colis

#### 3. Test Assignation
```java
testAssignationColis(colisService, livreurService)
```
- Assignation de colis aux livreurs
- Liste des colis par livreur
- Liste des colis non assignés

#### 4. Test Suivi des Statuts
```java
testSuiviStatuts(colisService)
```
- Mise à jour des statuts
- Liste des colis par statut

---

## 🔒 Gestion des Transactions

Le projet utilise **Spring Transaction Management** :

```java
@Transactional
public class ColisServiceImpl implements ColisService {
    // Toutes les méthodes sont transactionnelles
    // Rollback automatique en cas d'erreur
}
```

**Configuration dans `applicationContext.xml` :**

```xml
<tx:annotation-driven transaction-manager="transactionManager"/>
```

---

## 🌟 Points Forts du Projet

### Architecture

- ✅ **Séparation des couches** (Model, Repository, Service)
- ✅ **Injection de dépendances** via configuration XML
- ✅ **3 types d'injection** (Constructeur, Setter, Champ)
- ✅ **Gestion des transactions** automatique
- ✅ **Pool de connexions** avec HikariCP

### Qualité du Code

- ✅ **Validation des données** métier
- ✅ **Gestion des erreurs** complète
- ✅ **Code modulaire** et réutilisable
- ✅ **Documentation** exhaustive
- ✅ **Tests** de la logique métier

### Performances

- ✅ **Connection pooling** pour optimiser les connexions DB
- ✅ **Lazy loading** pour les relations
- ✅ **Cache de premier niveau** Hibernate
- ✅ **Scopes adaptés** (Singleton/Prototype)

---

## 🔧 Commandes Utiles

### Maven

```bash
# Nettoyer le projet
mvn clean

# Compiler
mvn compile

# Créer le JAR
mvn package

# Exécuter l'application
mvn exec:java

# Nettoyer et compiler
mvn clean compile

# Nettoyer, compiler et packager
mvn clean package
```

### Git

```bash
# Initialiser le repository
git init

# Ajouter tous les fichiers
git add .

# Commit
git commit -m "Initial commit: Smart Delivery System"

# Ajouter remote
git remote add origin https://github.com/votre-username/smart-delivery-system.git

# Pousser vers GitHub
git push -u origin main
```

### PostgreSQL

```bash
# Se connecter à PostgreSQL
psql -U postgres

# Créer la base
CREATE DATABASE smart_delivery_db;

# Lister les bases
\l

# Se connecter à la base
\c smart_delivery_db

# Lister les tables
\dt

# Quitter
\q
```

---

## 🐛 Résolution des Problèmes

### Problème : Base de données n'existe pas

**Erreur :**
```
FATAL: la base de données « smart_delivery_db » n'existe pas
```

**Solution :**
```bash
psql -U postgres -c "CREATE DATABASE smart_delivery_db;"
```

### Problème : Erreur de connexion PostgreSQL

**Vérifier que PostgreSQL est démarré :**
```bash
pg_isready -h localhost -p 5432
```

### Problème : Port déjà utilisé

**Modifier le port dans `application.properties` :**
```properties
db.url=jdbc:postgresql://localhost:5433/smart_delivery_db
```

---

## 📈 Évolutions Futures

### Version 2.0 (Prévue)

- 🔄 **API REST** avec Spring MVC
- 🔄 **Interface Web** pour la gestion
- 🔄 **Authentification** et autorisation
- 🔄 **Rapports** et statistiques
- 🔄 **Notifications** en temps réel
- 🔄 **Historique** des livraisons
- 🔄 **Géolocalisation** des livreurs
- 🔄 **Optimisation** des tournées

---

<div align="center">

**⭐ Smart Delivery Management System - Projet réalisé dans le cadre de la formation ⭐**

</div>

