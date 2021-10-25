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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
                if(reservierung.getFahrzeugId() == fahrzeug.getId() && reservierung.getDateDebut() == reservierungForm.getDateDebut()){
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
        Fahrzeug[] fahrzeugs = restTemplate.getForObject( urlFahrzeug + "/Fahrzeug", Fahrzeug[].class );
        for (Fahrzeug fahrzeug : fahrzeugs) {
            if(fahrzeug.getId() == id){
                int prix = currentReservierungForm.getNombreKm()*fahrzeug.getTarifKm()+fahrzeug.getPrixReservation();
                model.addAttribute("fahrzeug", fahrzeug);
                model.addAttribute("prix", prix);
            }
        }

        return "reservierung";
    }

    @RequestMapping(value = { "/reservierung/{id}" }, method = RequestMethod.POST)
    public String sendBenutzer(Model model) {
        Fahrzeug[] fahrzeugs = restTemplate.getForObject( urlFahrzeug + "/Fahrzeug", Fahrzeug[].class );


        //Bonjour Juliette et Rodolphe. Aujourd'hui il faut penser à faire le post de notre benutzer et Reservierung. Bon courage !!!
        //bisous

        return "reservierung";
    }


}
