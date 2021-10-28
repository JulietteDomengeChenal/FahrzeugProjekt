package com.example.front.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

import javax.transaction.Transactional;
import javax.persistence.EntityManager;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Benutzer {

    private int id;
    private String nom;
    private String prenom;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date dateNaissance;
    private String motDePasse;
    private long numeroPermis;
    private int anneeObtention;


    public Benutzer() {
    }

    public Benutzer (String nom, String prenom, String motDePasse, Date dateNaissance, long numeroPermis, int anneeObtention) {
        this.nom = nom;
        this.prenom = prenom;
        this.motDePasse = motDePasse;
        this.dateNaissance = dateNaissance;
        this.numeroPermis = numeroPermis;
        this.anneeObtention = anneeObtention;
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
    public String getMotDePasse() {
        return motDePasse;
    }
    public Date getDateNaissance() {
        return dateNaissance;
    }
    public long getNumeroPermis() {
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
    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }
    public void setNumeroPermis(long numeroPermis) {
        this.numeroPermis = numeroPermis;
    }
    public void setAnneeObtention(int anneeObtention) {
        this.anneeObtention = anneeObtention;
    }

}
