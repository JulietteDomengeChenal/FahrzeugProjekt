package com.example.front.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Benutzer {

    private int id;
    private String nom;
    private String prenom;
    private Date dateNaissance;
    private int numeroPermis;
    private int anneeObtention;

    public Benutzer() {
    }

    //---------------------------------GETTER----------------------------------------//
    public int getId() {
        return id;
    }
    public String getNom() {
        return nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public Date getDateNaissance() {
        return dateNaissance;
    }
    public int getNumeroPermis() {
        return numeroPermis;
    }
    public int getAnneeObtention() {
        return anneeObtention;
    }

    //---------------------------------SETTER----------------------------------------//
    public void setId(int id) {
        this.id = id;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }
    public void setNumeroPermis(int numeroPermis) {
        this.numeroPermis = numeroPermis;
    }
    public void setAnneeObtention(int anneeObtention) {
        this.anneeObtention = anneeObtention;
    }

}
