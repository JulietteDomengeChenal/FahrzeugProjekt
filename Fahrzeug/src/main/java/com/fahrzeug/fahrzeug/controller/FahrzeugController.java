package com.fahrzeug.fahrzeug.controller;


import com.fahrzeug.fahrzeug.BDD.FahrzeugRepository;
import com.fahrzeug.fahrzeug.model.Fahrzeug;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class FahrzeugController {

    @Autowired
    private FahrzeugRepository fahrzeugRepository;


    @RequestMapping(value = "/Fahrzeug", method = RequestMethod.GET)
    public List<Fahrzeug> fahrzeugList() {
        return (List<Fahrzeug>) fahrzeugRepository.findAll();
    }

    @GetMapping(value = "/Fahrzeug/{id}")
    public Optional<Fahrzeug> afficherFahrzeugId(@PathVariable int id) {
        return fahrzeugRepository.findById(id);
    }

    @PostMapping(value = "/Fahrzeug")
    public void ajouterFahrzeug(@RequestBody Fahrzeug fahrzeug){
        fahrzeugRepository.save(fahrzeug);
    }
}
