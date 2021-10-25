package com.example.front.form;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class ReservierungForm {

    private String fahrzeugType;
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private int dateDebut;
    private int duree;
    private int nombreKm;

    //-------------------------------CONSTRUCTOR--------------------------------------//
    public ReservierungForm() { }

    public ReservierungForm(String fahrzeugType, int dateDebut, int duree, int nombreKm) {
        this.fahrzeugType = fahrzeugType;
        this.dateDebut = dateDebut;
        this.duree = duree;
        this.nombreKm = nombreKm;
    }

    //---------------------------------GETTER----------------------------------------//
    public String getFahrzeugType() {
        return fahrzeugType;
    }
    public int getDateDebut() {
        return dateDebut;
    }
    public int getDuree() {
        return duree;
    }
    public int getNombreKm() {
        return nombreKm;
    }

    //---------------------------------SETTER----------------------------------------//
    public void setFahrzeugType(String fahrzeugType) {
        this.fahrzeugType = fahrzeugType;
    }
    public void setDateDebut(int dateDebut) {
        this.dateDebut = dateDebut;
    }
    public void setDuree(int duree) {
        this.duree = duree;
    }
    public void setNombreKm(int nombreKm) {
        this.nombreKm = nombreKm;
    }
}
