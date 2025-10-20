package com.vertdrop.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "colis")
public class Colis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String destinataire;
    private String adresse;
    private double poids;
    private String statut;

    @ManyToOne
    @JoinColumn(name = "livreur_id")
    private Livreur livreur;

    public Colis() {}

    public Colis(String destinataire, String adresse, double poids, String statut, Livreur livreur) {
        this.destinataire = destinataire;
        this.adresse = adresse;
        this.poids = poids;
        this.statut = statut;
        this.livreur = livreur;
    }

    // Getters and setters
    public Long getId() { return id; }
    public String getDestinataire() { return destinataire; }
    public void setDestinataire(String destinataire) { this.destinataire = destinataire; }
    public String getAdresse() { return adresse; }
    public void setAdresse(String adresse) { this.adresse = adresse; }
    public double getPoids() { return poids; }
    public void setPoids(double poids) { this.poids = poids; }
    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }
    public Livreur getLivreur() { return livreur; }
    public void setLivreur(Livreur livreur) { this.livreur = livreur; }

    @Override
    public String toString() {
        return "Colis{" +
                "id=" + id +
                ", destinataire='" + destinataire + '\'' +
                ", adresse='" + adresse + '\'' +
                ", poids=" + poids +
                ", statut='" + statut + '\'' +
                '}';
    }
}
