package com.example.front.form;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class LoginForm {

    private String nom;
    private String prenom;
    private String motDePasse;

    public LoginForm() {
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


    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

}
