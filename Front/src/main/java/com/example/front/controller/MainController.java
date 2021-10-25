package com.example.front.controller;


import com.example.front.form.ReservierungForm;
import com.example.front.model.Fahrzeug;
import com.example.front.model.Reservierung;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.Banner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;

@Controller
public class MainController {

    private RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();
    private List<Reservierung> reservierungs;
    private List<Fahrzeug> fahrzeugs;

    String urlReservierung = "http://localhost:8087";
    String urlFahrzeug = "http://localhost:8082";

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

    @RequestMapping(value = { "/recherche" }, method = RequestMethod.GET)
    public String goReservierung(Model model) {
        ReservierungForm reservierungForm = new ReservierungForm();
        model.addAttribute("reservierungForm", reservierungForm);
        return "recherche";
    }

//----------------Tableau-liste des véhicules /dispos/------------------------------------------------------------------
    @RequestMapping(value = { "/listeFahrzeug" }, method = RequestMethod.GET)
    public String findAllFahrzeug(Model model) {
        assert restTemplate != null;
        fahrzeugs = restTemplate.getForObject( urlFahrzeug + "/Fahrzeug", List.class );
        model.addAttribute("fahrzeugs", fahrzeugs);
        return "listeFahrzeug";
    }


    @RequestMapping(value = { "/recherche" }, method = RequestMethod.POST)
    public String saveReservierung(Model model, @ModelAttribute("reservierungForm") ReservierungForm reservierungForm){
        int benutzerId = reservierungForm.getBenutzerId();
        int fahrzeugId = reservierungForm.getFahrzeugId();
        String fahrzeugType = reservierungForm.getFahrzeugType();
        Date dateDebut = reservierungForm.getDateDebut();
        int duree = reservierungForm.getDuree();
        int prix = reservierungForm.getPrix();

        if(benutzerId != 0) {
            headers.setContentType(MediaType.APPLICATION_JSON);
            Reservierung reservierung = new Reservierung(benutzerId, fahrzeugId, fahrzeugType, dateDebut, duree, prix);

            HttpEntity<Reservierung> httpEntity = new HttpEntity<>(reservierung, headers);
            restTemplate.postForObject(urlReservierung + "/Reservierung", httpEntity, Reservierung.class);
            return "redirect:/liste";
        }
        model.addAttribute("errorMessage", errorMessage);
        return "recherche";
    }


}
