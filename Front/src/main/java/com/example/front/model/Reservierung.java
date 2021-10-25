package com.example.front.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Reservierung {

    private int id;
    private int benutzerId;
    private int fahrzeugId;
    private String fahrzeugType;
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private int dateDebut;
    private int duree;
    private int prix;

    @Override
    public String toString() {
        return "Reservierung{" + "id=" + id +
                ", benutzerId=" + benutzerId +
                ", fahrzeugId=" + fahrzeugId +
                ", fahrzeugType='" + fahrzeugType + '\'' +
                ", dateDebut=" + dateDebut +
                ", duree=" + duree +
                ", prix=" + prix + '}';
    }

    //-------------------------------CONSTRUCTOR--------------------------------------//
    public Reservierung() {
    }

    public Reservierung(int benutzerId, int fahrzeugId, String fahrzeugType, int dateDebut, int duree, int prix) {
        this.benutzerId = benutzerId;
        this.fahrzeugId = fahrzeugId;
        this.fahrzeugType = fahrzeugType;
        this.dateDebut = dateDebut;
        this.duree = duree;
        this.prix = prix;
    }

    //---------------------------------GETTER----------------------------------------//
    public int getId() {
        return id;
    }
    public int getBenutzerId() {
        return benutzerId;
    }
    public int getFahrzeugId() {
        return fahrzeugId;
    }
    public String getFahrzeugType() {
        return fahrzeugType;
    }
    public int getDateDebut() {
        return dateDebut;
    }
    public int getDuree() {
        return duree;
    }
    public int getPrix() {
        return prix;
    }

    //---------------------------------SETTER----------------------------------------//
    public void setId(int id) {
        this.id = id;
    }
    public void setBenutzerId(int benutzerId) {
        this.benutzerId = benutzerId;
    }
    public void setFahrzeugId(int fahrzeugId) {
        this.fahrzeugId = fahrzeugId;
    }
    public void setFahrzeugType(String fahrzeugType) {
        this.fahrzeugType = fahrzeugType;
    }
    public void setDateDebut(int dateDebut) {
        this.dateDebut = dateDebut;
    }
    public void setDuree(int duree) {
        this.duree = duree;
    }
    public void setPrix(int prix) {
        this.prix = prix;
    }


}
