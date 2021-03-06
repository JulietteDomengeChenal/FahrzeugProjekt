package com.benutzer.benutzer.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

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
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dateNaissance;

    @Column(name = "numeroPermis")
    private long numeroPermis;

    @Column(name= "motDePasse")
    private String motDePasse;

    @Column(name = "anneeObtention")
    private int anneeObtention;

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

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public void setAnneeObtention(int anneeObtention) {
        this.anneeObtention = anneeObtention;
    }
}
