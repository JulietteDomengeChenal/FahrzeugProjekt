package com.benutzer.benutzer.model;

import javax.persistence.*;

@Entity
@Table(name= "Benutzer")
public class Benutzer {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "dateNaissance")
    private int dateNaissance;

    @Column(name = "numeroPermis")
    private int numeroPermis;

    @Column(name = "anneeObtention")
    private String anneeObtention;

    public Benutzer() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(int dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public int getNumeroPermis() {
        return numeroPermis;
    }

    public void setNumeroPermis(int numeroPermis) {
        this.numeroPermis = numeroPermis;
    }

    public String getAnneeObtention() {
        return anneeObtention;
    }

    public void setAnneeObtention(String anneeObtention) {
        this.anneeObtention = anneeObtention;
    }
}
