package com.vertdrop.dto;

public class CreateColisRequest {
    private String destinataire;
    private String adresse;
    private double poids;
    private Long livreurId;

    public String getDestinataire() { return destinataire; }
    public void setDestinataire(String destinataire) { this.destinataire = destinataire; }
    public String getAdresse() { return adresse; }
    public void setAdresse(String adresse) { this.adresse = adresse; }
    public double getPoids() { return poids; }
    public void setPoids(double poids) { this.poids = poids; }
    public Long getLivreurId() { return livreurId; }
    public void setLivreurId(Long livreurId) { this.livreurId = livreurId; }
}
