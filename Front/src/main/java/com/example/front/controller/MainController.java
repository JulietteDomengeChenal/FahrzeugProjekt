package com.example.front.controller;


import com.example.front.form.BenutzerForm;
import com.example.front.form.ReservierungForm;
import com.example.front.model.Benutzer;
import com.example.front.model.Fahrzeug;
import com.example.front.model.Reservierung;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.Banner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Controller
public class MainController {

    private RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();
    private List<Reservierung> reservierungs;
    private List<Fahrzeug> fahrzeugs;
    private ReservierungForm currentReservierungForm = new ReservierungForm();

    String urlReservierung = "http://localhost:8087";
    String urlFahrzeug = "http://localhost:8082";
    String urlBenutzer = "http://localhost:8088";

    // Injectez (inject) via application.properties.
    @Value("${welcome.message}")
    private String message;
    @Value("${error.message}")
    private String errorMessage;


//----------------Tableau-liste des réservations------------------------------------------------------------------------
    @RequestMapping(value = { "/", "/accueil" }, method = RequestMethod.GET)
    public String index(Model model) {
        assert restTemplate != null;
        reservierungs = restTemplate.getForObject( urlReservierung + "/ListeReservierung", List.class );
        model.addAttribute("reservierungs", reservierungs);
        return "accueil";
    }

//----------------Afficher le formulaire--------------------------------------------------------------------------------
    @RequestMapping(value = { "/recherche" }, method = RequestMethod.GET)
    public String showFormulaire(Model model){
        ReservierungForm reservierungForm = new ReservierungForm();
        model.addAttribute("reservierungForm", reservierungForm);
        return "recherche";
    }

//----------------Enregistrer le formulaire-----------------------------------------------------------------------------
    @RequestMapping(value = { "/recherche" }, method = RequestMethod.POST)
    public String findAllFahrzeug(Model model, @ModelAttribute("reservierungForm") ReservierungForm reservierungForm) {
        Fahrzeug[] fahrzeugs = restTemplate.getForObject( urlFahrzeug + "/Fahrzeug", Fahrzeug[].class );
        Reservierung[] reservierungs = restTemplate.getForObject( urlReservierung + "/ListeReservierung", Reservierung[].class );
        currentReservierungForm = reservierungForm;

        List<Fahrzeug> selectedFahrzeug = new ArrayList<>();

        for (Fahrzeug fahrzeug : fahrzeugs) {
            if (reservierungForm.getFahrzeugType().equals(fahrzeug.getType())){
                selectedFahrzeug.add(fahrzeug);
            }
            for (Reservierung reservierung : reservierungs){
                if(reservierung.getFahrzeugId() == fahrzeug.getId() && (
//                     (reservierungForm.getDateDebut().before(reservierung.getDateFin()) && reservierungForm.getDateDebut().after(reservierung.getDateDebut()))
//                     || (reservierungForm.getDateFin().after(reservierung.getDateDebut()) && reservierungForm.getDateDebut().before(reservierung.getDateFin()))
                    (!reservierung.getDateFin().before(reservierungForm.getDateDebut()) && !reservierung.getDateDebut().after(reservierungForm.getDateDebut()))
                    || (!reservierung.getDateDebut().after(reservierungForm.getDateFin()) && !reservierung.getDateFin().before(reservierungForm.getDateDebut()))
                )){
                    selectedFahrzeug.remove(fahrzeug);
                }
            }
        }
        model.addAttribute("selectedFahrzeug", selectedFahrzeug);
        model.addAttribute("reservierungForm", reservierungForm);

        return "listeFahrzeug";

    }

    @RequestMapping(value = { "/reservierung/{id}" }, method = RequestMethod.GET)
    public String selectedFahrzeug(Model model, @PathVariable int id) {
        int prix = 0;
        String fahrzeugType = null;
        Fahrzeug[] fahrzeugs = restTemplate.getForObject( urlFahrzeug + "/Fahrzeug", Fahrzeug[].class );
        for (Fahrzeug fahrzeug : fahrzeugs) {
            if(fahrzeug.getId() == id){
                prix = currentReservierungForm.getNombreKm()*fahrzeug.getTarifKm()+fahrzeug.getPrixReservation();
                fahrzeugType = fahrzeug.getType();
                model.addAttribute("fahrzeug", fahrzeug);
                model.addAttribute("prix", prix);
            }
        }

        BenutzerForm benutzerForm = new BenutzerForm();
        benutzerForm.setPrix(prix);
        benutzerForm.setFahrzeugType(fahrzeugType);
        model.addAttribute("benutzerForm", benutzerForm);

        return "reservierung";
    }

    @RequestMapping(value = { "/reservierung/{id}" }, method = RequestMethod.POST)
    public String sendBenutzerReservierung(Model model, @ModelAttribute("benutzerForm") BenutzerForm benutzerForm,
            @PathVariable int id) {

        Benutzer benutzer = new Benutzer(benutzerForm.getNom(),benutzerForm.getPrenom(),benutzerForm.getDateNaissance(),
                benutzerForm.getNumeroPermis(),benutzerForm.getAnneeObtention());
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Benutzer> httpEntity = new HttpEntity<>(benutzer, headers);
        benutzer = restTemplate.postForObject(urlBenutzer + "/Benutzer",httpEntity,Benutzer.class);

        Reservierung reservierung = new Reservierung(benutzer.getId(), id, benutzerForm.getFahrzeugType(),
                currentReservierungForm.getDateDebut(), currentReservierungForm.getDateFin(), benutzerForm.getPrix());
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Reservierung> http2Entity = new HttpEntity<>(reservierung, headers);
        restTemplate.postForObject(urlReservierung + "/Reservierung", http2Entity, Reservierung.class);

        return "confirmation";
    }

}
