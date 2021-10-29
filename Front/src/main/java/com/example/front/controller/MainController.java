package com.example.front.controller;


import com.example.front.form.BenutzerForm;
import com.example.front.form.LoginForm;
import com.example.front.form.NewBenutzerForm;
import com.example.front.form.ReservierungForm;
import com.example.front.model.Benutzer;
import com.example.front.model.Fahrzeug;
import com.example.front.model.Reservierung;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.text.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

@Controller
public class MainController {

    private RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();
    private List<Reservierung> reservierungs;
    private List<Fahrzeug> fahrzeugs;
    private ReservierungForm currentReservierungForm = new ReservierungForm();
    private final static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private Benutzer currentBenutzer = new Benutzer();

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
        model.addAttribute("currentBenutzer", currentBenutzer);
        return "accueil";
    }

//----------------Afficher le formulaire de Login-----------------------------------------------------------------------
    @RequestMapping(value = { "/login" }, method = RequestMethod.GET)
    public String showFormulaireLogin(Model model){
        currentBenutzer = null;
        LoginForm loginForm = new LoginForm();
        model.addAttribute("loginForm", loginForm);
        model.addAttribute("currentBenutzer", currentBenutzer);

        return "login";
    }

//----------------Rechercher l'utilisateur-----------------------------------------------------------------------------
    @RequestMapping(value = { "/login" }, method = RequestMethod.POST)
    public String findBenutzer(Model model, @ModelAttribute("loginForm") LoginForm loginForm) {
        Benutzer[] benutzers = restTemplate.getForObject( urlBenutzer + "/Benutzer", Benutzer[].class );
        for (Benutzer benutzer : benutzers){
            if (benutzer.getNom().contains(loginForm.getNom()) &&
            benutzer.getPrenom().contains(loginForm.getPrenom()) &&
            benutzer.getMotDePasse().contains(loginForm.getMotDePasse())){
                currentBenutzer = benutzer;
            }
        }
        model.addAttribute("currentBenutzer", currentBenutzer);
        return "redirect:/recherche";
    }

//----------------Afficher le formulaire de new Benutzer----------------------------------------------------------------
    @RequestMapping(value = { "/newBenutzer" }, method = RequestMethod.GET)
    public String showFormulairenewBenutzer(Model model){
        NewBenutzerForm newBenutzerForm = new NewBenutzerForm();
        model.addAttribute("newBenutzerForm", newBenutzerForm);
        model.addAttribute("currentBenutzer", currentBenutzer);
        return "newBenutzer";
    }

//----------------Enregistrer nouvel utilisateur------------------------------------------------------------------------
    @RequestMapping(value = { "/newBenutzer" }, method = RequestMethod.POST)
    public String addBenutzer(Model model, @ModelAttribute("newBenutzerForm") NewBenutzerForm newBenutzerForm) {
        Benutzer benutzer = new Benutzer(newBenutzerForm.getNom(), newBenutzerForm.getPrenom(),
                newBenutzerForm.getMotDePasse(), newBenutzerForm.getDateNaissance(),
                newBenutzerForm.getNumeroPermis(), newBenutzerForm.getAnneeObtention());
        currentBenutzer = benutzer;

        String pattern = "yyyy-MM-dd";
        DateFormat df = new SimpleDateFormat(pattern);

        Date today = Calendar.getInstance().getTime();
        String todayAsString = df.format(today);
        LocalDate todayParsed = LocalDate.parse(todayAsString);

        String birth = df.format(currentBenutzer.getDateNaissance());
        LocalDate date = LocalDate.parse(birth);

        Period period = Period.between(date, todayParsed);
        int age = Math.abs(period.getYears());

        if(age < 18){
            return "tropJeune";
        }

        String numString = Long.toString(currentBenutzer.getNumeroPermis());
        if(numString.length() != 12){
            return "immatInvalide";
        }
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Benutzer> httpEntity = new HttpEntity<>(currentBenutzer, headers);
        currentBenutzer = restTemplate.postForObject(urlBenutzer + "/Benutzer", httpEntity, Benutzer.class);
        model.addAttribute("currentBenutzer", currentBenutzer);
        return "redirect:/recherche";
    }


//----------------Afficher le formulaire--------------------------------------------------------------------------------
    @RequestMapping(value = { "/recherche" }, method = RequestMethod.GET)
    public String showFormulaire(Model model){
        ReservierungForm reservierungForm = new ReservierungForm();
        model.addAttribute("reservierungForm", reservierungForm);
        model.addAttribute("currentBenutzer", currentBenutzer);
        return "recherche";
    }

//----------------Enregistrer le formulaire-----------------------------------------------------------------------------
    @RequestMapping(value = { "/recherche" }, method = RequestMethod.POST)
    public String findAllFahrzeug(Model model, @ModelAttribute("reservierungForm") ReservierungForm reservierungForm) {
        Fahrzeug[] fahrzeugs = restTemplate.getForObject( urlFahrzeug + "/Fahrzeug", Fahrzeug[].class );
        Reservierung[] reservierungs = restTemplate.getForObject( urlReservierung + "/ListeReservierung", Reservierung[].class );
        currentReservierungForm = reservierungForm;

        String pattern = "yyyy-MM-dd";
        DateFormat df = new SimpleDateFormat(pattern);

        Date today = Calendar.getInstance().getTime();
        String todayAsString = df.format(today);
        LocalDate todayParsed = LocalDate.parse(todayAsString);

        String birth = df.format(currentBenutzer.getDateNaissance());
        LocalDate date = LocalDate.parse(birth);

        Period period = Period.between(date, todayParsed);
        int age = Math.abs(period.getYears());

        List<Fahrzeug> selectedFahrzeug = new ArrayList<>();

        for (Fahrzeug fahrzeug : fahrzeugs) {
            if (reservierungForm.getFahrzeugType().equals(fahrzeug.getType()) && age>25){
                selectedFahrzeug.add(fahrzeug);
            }
            else if(age<21 && fahrzeug.getChevauxFiscaux()<8 && reservierungForm.getFahrzeugType().equals(fahrzeug.getType())){
                selectedFahrzeug.add(fahrzeug);
            }
            else if(age<25 && age>=21 && fahrzeug.getChevauxFiscaux()<13 && reservierungForm.getFahrzeugType().equals(fahrzeug.getType())){
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
        model.addAttribute("currentBenutzer", currentBenutzer);
        return "listeFahrzeug";

    }

    @RequestMapping(value = { "/reservierung/{id}" }, method = RequestMethod.GET)
    public String selectedFahrzeug(Model model, @PathVariable int id) {
        double prix = 0;
        String prixAffiche = null;
        String fahrzeugType = null;
        Fahrzeug[] fahrzeugs = restTemplate.getForObject( urlFahrzeug + "/Fahrzeug", Fahrzeug[].class );
        for (Fahrzeug fahrzeug : fahrzeugs) {
            if(fahrzeug.getId() == id){
                if(fahrzeug.getType().contains("wagen")) {
                    prix = currentReservierungForm.getNombreKm() * fahrzeug.getTarifKm() + fahrzeug.getPrixReservation();
                }
                else if(fahrzeug.getType().contains("motorrad")){
                    prix = currentReservierungForm.getNombreKm() * fahrzeug.getTarifKm() * 0.001 * fahrzeug.getCylindree() + fahrzeug.getPrixReservation();
                }
                else if(fahrzeug.getType().contains("nutzfahrzeug")){
                    prix = currentReservierungForm.getNombreKm() * fahrzeug.getTarifKm() * 0.05 * fahrzeug.getVolumeChargement() + fahrzeug.getPrixReservation();
                }
                NumberFormat formatter = new DecimalFormat("#0.00");
                prixAffiche=formatter.format(prix);
                fahrzeugType = fahrzeug.getType();
                model.addAttribute("prixAffiche", prixAffiche);
                model.addAttribute("fahrzeug", fahrzeug);
                model.addAttribute("prix", prix);
            }
        }

        BenutzerForm benutzerForm = new BenutzerForm();
        benutzerForm.setPrix(prix);
        benutzerForm.setFahrzeugType(fahrzeugType);
        model.addAttribute("benutzerForm", benutzerForm);
        model.addAttribute("currentBenutzer", currentBenutzer);

        return "reservierung";
    }

    @RequestMapping(value = { "/reservierung/{id}" }, method = RequestMethod.POST)
    public String sendBenutzerReservierung(Model model, @ModelAttribute("benutzerForm") BenutzerForm benutzerForm,
            @PathVariable int id) {

//        Benutzer benutzer = new Benutzer(benutzerForm.getNom(),benutzerForm.getPrenom(), benutzerbenutzerForm.getDateNaissance(),
//                benutzerForm.getNumeroPermis(),benutzerForm.getAnneeObtention());
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<Benutzer> httpEntity = new HttpEntity<>(currentBenutzer, headers);
//        currentBenutzer = restTemplate.postForObject(urlBenutzer + "/Benutzer",httpEntity,Benutzer.class);

        Reservierung reservierung = new Reservierung(currentBenutzer.getId(), id, benutzerForm.getFahrzeugType(),
                currentReservierungForm.getDateDebut(), currentReservierungForm.getDateFin(), benutzerForm.getPrix());
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Reservierung> http2Entity = new HttpEntity<>(reservierung, headers);
        restTemplate.postForObject(urlReservierung + "/Reservierung", http2Entity, Reservierung.class);

        model.addAttribute("currentBenutzer", currentBenutzer);
        return "confirmation";
    }

//----------------Afficher mon compte-----------------------------------------------------------------------------------
    @RequestMapping(value = { "/monCompte" }, method = RequestMethod.GET)
    public String showMonCompte(Model model){

        List<Reservierung> selectedReservierungs= new ArrayList<>();
        Reservierung[] reservierungs = restTemplate.getForObject( urlReservierung + "/ListeReservierung", Reservierung[].class );
        List<Fahrzeug> selectedFahrzeug= new ArrayList<>();
        Fahrzeug[] fahrzeugs = restTemplate.getForObject( urlFahrzeug + "/Fahrzeug", Fahrzeug[].class );

        for (Reservierung reservierung : reservierungs){
            if(reservierung.getBenutzerId() == currentBenutzer.getId()){
                selectedReservierungs.add(reservierung);
            }
        }
        for (Reservierung reservierung : selectedReservierungs) {
            for (Fahrzeug fahrzeug : fahrzeugs) {
                if (fahrzeug.getId() == reservierung.getFahrzeugId()) {
                    selectedFahrzeug.add(fahrzeug);
                }
            }
        }
        model.addAttribute("selectedFahrzeug", selectedFahrzeug);
        model.addAttribute("selectedReservierungs", selectedReservierungs);
        model.addAttribute("currentBenutzer", currentBenutzer);
        return "monCompte";
    }

    @GetMapping
    public String main(Model model) throws ParseException {
        model.addAttribute("exampleDate", sdf.parse("20/06/2020"));
        return "qqchose";
    }


}
