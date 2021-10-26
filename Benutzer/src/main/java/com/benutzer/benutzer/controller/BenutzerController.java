package com.benutzer.benutzer.controller;

import com.benutzer.benutzer.BDD.BenutzerRepository;
import com.benutzer.benutzer.model.Benutzer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RestController
public class BenutzerController {

    @Autowired
    private BenutzerRepository benutzerRepository;

    @RequestMapping(value = "/Benutzer", method = RequestMethod.GET)
    public List<Benutzer> benutzerList() {
        return (List<Benutzer>) benutzerRepository.findAll();
    }

    @GetMapping(value = "/Benutzer/{id}")
    public Optional<Benutzer> afficherFahrzeugId(@PathVariable int id) {
        return benutzerRepository.findById(id);
    }

    @PostMapping(value= "/Benutzer")
    public Benutzer ajouterPersonnage(@RequestBody Benutzer benutzer){
        benutzerRepository.save(benutzer);
        return benutzer;
    }
}
