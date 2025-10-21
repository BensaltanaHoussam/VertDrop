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

    private StatusColis status;

    @ManyToOne
    @JoinColumn(name = "livreur_id")
    private Livreur livreur;

    public Colis(String destinataire, String adresse, double poids, StatusColis status, Livreur livreur) {
        this.destinataire = destinataire;
        this.adresse = adresse;
        this.poids = poids;
        this.status = status;
        this.livreur = livreur;
    }

    // Getters et setters
    public Long getId() {
        return id;
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

    public double getPoids() {
        return poids;
    }

    public void setPoids(double poids) {
        this.poids = poids;
    }

    public StatusColis getStatus() {
        return status;
    }

    public void setStatus(StatusColis status) {
        this.status = status;
    }

    public Livreur getLivreur() {
        return livreur;
    }

    public void setLivreur(Livreur livreur) {
        this.livreur = livreur;
    }

    @Override
    public String toString() {
        return "Colis{" +
                "id=" + id +
                ", destinataire='" + destinataire + '\'' +
                ", adresse='" + adresse + '\'' +
                ", poids=" + poids +
                ", status=" + status +
                '}';
    }
}
