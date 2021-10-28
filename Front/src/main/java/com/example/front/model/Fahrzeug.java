package com.example.front.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Fahrzeug {

    private int id;
    private String type;
    private String immatriculation;
    private String marque;
    private String modele;
    private String couleur;
    private int prixReservation;
    private double tarifKm ;
    private int chevauxFiscaux;
    private int cylindree;
    private int volumeChargement;
    private String image;

    public Fahrzeug() {

    }

    //---------------------------------GETTER----------------------------------------//
    public int getId() {
        return id;
    }
    public String getType() {
        return type;
    }
    public String getImmatriculation() {
        return immatriculation;
    }
    public String getMarque() {
        return marque;
    }
    public String getModele() {
        return modele;
    }
    public String getCouleur() {
        return couleur;
    }
    public int getPrixReservation() {
        return prixReservation;
    }
    public double getTarifKm() {
        return tarifKm;
    }
    public int getChevauxFiscaux() {
        return chevauxFiscaux;
    }
    public int getCylindree() {
        return cylindree;
    }
    public int getVolumeChargement() {
        return volumeChargement;
    }
    public String getImage() {
        return image;
    }

    //---------------------------------SETTER----------------------------------------//
    public void setId(int id) {
        this.id = id;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }
    public void setMarque(String marque) {
        this.marque = marque;
    }
    public void setModele(String modele) {
        this.modele = modele;
    }
    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }
    public void setPrixReservation(int prixReservation) {
        this.prixReservation = prixReservation;
    }
    public void setTarifKm(double tarifKm) {
        this.tarifKm = tarifKm;
    }
    public void setChevauxFiscaux(int chevauxFiscaux) {
        this.chevauxFiscaux = chevauxFiscaux;
    }
    public void setCylindree(int cylindree) {
        this.cylindree = cylindree;
    }
    public void setVolumeChargement(int volumeChargement) {
        this.volumeChargement = volumeChargement;
    }
    public void setImage(String image) {
        this.image = image;
    }
}
