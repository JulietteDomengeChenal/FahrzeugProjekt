package com.example.front.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Reservierung {

    private int id;
    private int benutzerId;
    private int fahrzeugId;
    private String fahrzeugType;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date dateDebut;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date dateFin;
    private double prix;

    @Override
    public String toString() {
        return "Reservierung{" + "id=" + id +
                ", benutzerId=" + benutzerId +
                ", fahrzeugId=" + fahrzeugId +
                ", fahrzeugType='" + fahrzeugType + '\'' +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", prix=" + prix + '}';
    }

    //-------------------------------CONSTRUCTOR--------------------------------------//
    public Reservierung() {
    }

    public Reservierung(int benutzerId, int fahrzeugId, String fahrzeugType, Date dateDebut, Date dateFin, double prix) {
        this.benutzerId = benutzerId;
        this.fahrzeugId = fahrzeugId;
        this.fahrzeugType = fahrzeugType;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
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
    public Date getDateDebut() {
        return dateDebut;
    }
    public Date getDateFin() {
        return dateFin;
    }
    public double getPrix() {
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
    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }
    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }
    public void setPrix(double prix) {
        this.prix = prix;
    }


}
