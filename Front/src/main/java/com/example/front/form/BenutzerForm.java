package com.example.front.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class BenutzerForm {

    private int id;
    private String nom;
    private String prenom;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date dateNaissance;
    private int numeroPermis;
    private int anneeObtention;
    private double prix;
    private String fahrzeugType;


    //-------------------------------CONSTRUCTOR--------------------------------------//
    public BenutzerForm() {
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
    public double getPrix() {
        return prix;
    }
    public String getFahrzeugType() {
        return fahrzeugType;
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
    public void setPrix(double prix) {
        this.prix = prix;
    }
    public void setFahrzeugType(String fahrzeugType) {
        this.fahrzeugType = fahrzeugType;
    }

}
