package com.example.front.form;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class NewBenutzerForm {

    private int id;
    private String nom;
    private String prenom;
    private String motDePasse;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date dateNaissance;
    private long numeroPermis;
    private int anneeObtention;

    public NewBenutzerForm() {
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

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public long getNumeroPermis() {
        return numeroPermis;
    }

    public void setNumeroPermis(long numeroPermis) {
        this.numeroPermis = numeroPermis;
    }

    public int getAnneeObtention() {
        return anneeObtention;
    }

    public void setAnneeObtention(int anneeObtention) {
        this.anneeObtention = anneeObtention;
    }
}
