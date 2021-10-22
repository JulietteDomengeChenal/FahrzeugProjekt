package com.example.datamySql;

import com.example.reservierung.Reservierung;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReservierungRepository extends CrudRepository<Reservierung, Integer> {

    List<Reservierung> findAll();

    Reservierung findById(int id);

    Reservierung save(Reservierung reservierung);

    void deleteById(int id);

}
