# VertDrop Smart Delivery Management System 

Système centralisé pour moderniser la gestion des livraisons : réduction des erreurs de saisie, suppression des doublons, meilleure planification et visibilité en temps réel.

## Contexte

Problèmes actuels observés avec les fichiers Excel \/\ registres papier :
- Erreurs de saisie et doublons.
- Retards dus à une information dispersée.
- Perte de données et visibilité limitée sur l’état des livraisons.

Objectifs :
- Centraliser les données colis \/ livreurs.
- Éviter erreurs et doublons.
- Améliorer planification et visibilité.

## User Stories

- Gérer les livreurs \(CRUD\) pour fiabiliser les données.
- Enregistrer un colis et l’assigner à un livreur.
- Mettre à jour le statut d’un colis \(préparation, en\_transit, livré\).
- Lister les colis d’un livreur pour planifier les tournées.
- Supprimer \/ corriger une information erronée.

## Périmètre technique

- Spring Core \(IoC, DI, Beans, Scopes\)
- Spring Data JPA
- Spring MVC \(API REST \- bonus, présent\)
- PostgreSQL
- Maven
- Postman \(tests API\)

Injection de dépendances \(DI\) \:
- Par constructeur \(recommandée, utilisée dans les contrôleurs\/services\).
- Par setter \(démontrable sur des beans de configuration\).
- Par champ \(à éviter en production, montrable à titre pédagogique\).

## Modèle de données

- Livreur \: id, nom, prénom, véhicule, téléphone.
- Colis \: id, destinataire, adresse, poids, statut \[PREPARATION, EN\_TRANSIT, LIVRE\], idLivreur.

Relation \: `Livreur 1 \-\> N Colis`.

## Démarrage rapide

Prérequis \:
- JDK 17\+
- Maven 3\.9\+
- PostgreSQL 14\+

Configuration `src/main/resources/application.properties` \:
