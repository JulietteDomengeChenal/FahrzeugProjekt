package com.fahrzeug.fahrzeug.BDD;

import com.fahrzeug.fahrzeug.model.Fahrzeug;
import org.springframework.data.repository.CrudRepository;

public interface FahrzeugRepository extends CrudRepository<Fahrzeug, Integer> {

}
