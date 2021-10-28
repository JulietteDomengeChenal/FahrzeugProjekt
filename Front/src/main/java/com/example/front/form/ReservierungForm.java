package com.example.front.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class ReservierungForm {

    private String fahrzeugType;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date dateDebut;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date dateFin;
    private int age;
    private int nombreKm;

    //-------------------------------CONSTRUCTOR--------------------------------------//
    public ReservierungForm() { }

    public ReservierungForm(String fahrzeugType, Date dateDebut, Date dateFin, int age, int nombreKm) {
        this.fahrzeugType = fahrzeugType;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.age = age;
        this.nombreKm = nombreKm;
    }

    //---------------------------------GETTER----------------------------------------//
    public String getFahrzeugType() {
        return fahrzeugType;
    }
    public Date getDateDebut() {
        return dateDebut;
    }
    public Date getDateFin() {
        return dateFin;
    }
    public int getAge() {
        return age;
    }
    public int getNombreKm() {
        return nombreKm;
    }

    //---------------------------------SETTER----------------------------------------//
    public void setFahrzeugType(String fahrzeugType) {
        this.fahrzeugType = fahrzeugType;
    }
    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }
    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void setNombreKm(int nombreKm) {
        this.nombreKm = nombreKm;
    }
}
