package com.fahrzeug.fahrzeug.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Fahrzeug")
public class Fahrzeug {

    @Id
    @GeneratedValue
    private int id;

    @Column(name="type")
    private String type;

    @Column(name="immatriculation")
    private String immatriculation;

    @Column(name="marque")
    private String marque;

    @Column(name="modele")
    private String modele;

    @Column(name="couleur")
    private String couleur;

    @Column(name="prixReservation")
    private int prixReservation;

    @Column(name="tarifKm")
    private int tarifKm ;

    @Column(name="chevauxFiscaux")
    private int chevauxFiscaux;

    @Column(name="cylindree")
    private int cylindree;

    @Column(name="volumeChargement")
    private int volumeChargement;

    public Fahrzeug() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public int getPrixReservation() {
        return prixReservation;
    }

    public void setPrixReservation(int prixReservation) {
        this.prixReservation = prixReservation;
    }

    public int getTarifKm() {
        return tarifKm;
    }

    public void setTarifKm(int tarifKm) {
        this.tarifKm = tarifKm;
    }

    public int getChevauxFiscaux() {
        return chevauxFiscaux;
    }

    public void setChevauxFiscaux(int chevauxFiscaux) {
        this.chevauxFiscaux = chevauxFiscaux;
    }

    public int getCylindree() {
        return cylindree;
    }

    public void setCylindree(int cylindree) {
        this.cylindree = cylindree;
    }

    public int getVolumeChargement() {
        return volumeChargement;
    }

    public void setVolumeChargement(int volumeChargement) {
        this.volumeChargement = volumeChargement;
    }
}
