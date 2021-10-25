package com.benutzer.benutzer.BDD;

import com.benutzer.benutzer.model.Benutzer;
import org.springframework.data.repository.CrudRepository;

public interface BenutzerRepository extends CrudRepository<Benutzer, Integer> {
}
