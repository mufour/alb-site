# ALB – Backend

## Présentation

Ce backend s'inscrit dans le projet de modernisation du site internet de l'Amicale Laïque de Bacalan.
L'objectif est de faire évoluer un site vitrine statique vers une application web administrable, afin de permettre à l'association de gérer ses contenus de manière autonome, sans modifier directement les fichiers HTML.
Le projet est développé progressivement, à partir du site existant.

### Stack technique

- **Java 21** – Langage backend
- **Spring Boot** – Framework applicatif
- **Spring Data JPA / Hibernate** – Persistance des données
- **PostgreSQL 16** – Base de données relationnelle
- **Docker Compose** – Environnement local de base de données
- **Flyway** – Versionnement et migrations SQL
- **Maven** – Gestion des dépendances et compilation
- **Bruno** – Tests manuels des endpoints REST
- **Git / GitHub** – Versionnement du projet

#### Architecture

Le backend repose sur une architecture en couches :
Controller -> Service -> Repository -> PostgreSQL
Les DTO et les mappers permettent de séparer les données exposées par l'API du modèle de persistance.

##### Base de données

La base PostgreSQL contient plusieurs tables, pour le moment :

- `news` : actualités
- `events` : événements
- `media` : médias
- `pages` : pages
- `partners` : partenaires
- `banner_images` : images du bandeau
- `team_members` : membres de l'équipe

Les migrations SQL sont gérées avec Flyway. La configuration de connexion est externalisée grâce à des variables d'environnement.

###### Travail réalisé

- Structuration du backend Spring Boot.
- Création des entités et des couches Controller, Service et Repository.
- Mise en place d'endpoints REST pour la gestion des contenus.
- Intégration de PostgreSQL avec Docker Compose.
- Création et exécution des migrations Flyway.
- Vérification des opérations CRUD avec Bruno.
- Vérification de la persistance des données après redémarrage.
- Compilation et validation du projet avec Maven.

##### Développement assisté par IA

Ce projet me permet également d'expérimenter une utilisation plus structurée de l'intelligence artificielle dans mon travail de développeur.

L'IA intervient notamment pour :
- Préparer et organiser les étapes de développement.
- Explorer des solutions techniques.
- Aider à la compréhension des erreurs et identifier des pistes de correction.

J'examine les propositions, j'adapte et vérifie à travers l'exécution de l'application et les tests.
Cette démarche me permet de gagner du temps tout en consolidant mes compétences techniques.

#### État du projet

**Projet en cours de développement.**

Prochaines étapes :

- Poursuivre l'implémentation des fonctionnalités métier.
- Compléter les tests.
- Développer et intégrer le back-office.
- Permettre aux utilisateurs autorisés de gérer les contenus du site.

### Contexte

Projet personnel de développement, réalisé dans une démarche de montée en compétences et de modernisation d'un site associatif existant.