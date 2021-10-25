package com.example.controller;

import com.example.datamySql.ReservierungRepository;
import com.example.reservierung.Reservierung;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReservierungController {

    @Autowired
    private ReservierungRepository reservierungRepository;

    //---------------------------------------Liste des réservations --------------------------------------------------------
    @GetMapping(value = "/ListeReservierung")
    public List<Reservierung> listReservierung() {
        return reservierungRepository.findAll();
    }

    //---------------------------------------Afficher une réservation/{id---------------------------------------------------
    @GetMapping(value = "/Reservierung/{id}")
    public Reservierung afficherReservierung(@PathVariable int id) {
        return reservierungRepository.findById(id);
    }

    //----------------------------------------Ajouter une réservation-------------------------------------------------------
    @PostMapping(value = "/Reservierung")
    public Reservierung addReservierung(@RequestBody Reservierung reservierung) {
        return reservierungRepository.save(reservierung);
    }

    //----------------------------------------Edit une réservation----------------------------------------------------------
    @PutMapping(value = "/Reservierung/{id}")
    public Reservierung editReservierung(@RequestBody Reservierung reservierung, @PathVariable int id) {
        Reservierung editReservierung = reservierungRepository.findById(id);
        editReservierung.setFahrzeugType(reservierung.getFahrzeugType());
        editReservierung.setFahrzeugId(reservierung.getFahrzeugId());
        editReservierung.setBenutzerId(reservierung.getBenutzerId());
        editReservierung.setDuree(reservierung.getDuree());
        editReservierung.setDateDebut(reservierung.getDateDebut());
        editReservierung.setPrix(reservierung.getPrix());
        return reservierungRepository.save(editReservierung);
    }

    //-------------------------------------------Delete une réservation-----------------------------------------------------
    @DeleteMapping(value = "/Reservierung/{id}")
    public void deleteReservierung(@PathVariable int id) {
        reservierungRepository.deleteById(id);
    }

}
