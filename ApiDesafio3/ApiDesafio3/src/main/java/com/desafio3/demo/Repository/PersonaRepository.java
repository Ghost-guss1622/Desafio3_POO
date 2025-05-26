package com.desafio3.demo.Repository;
import com.desafio3.demo.Model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface PersonaRepository extends JpaRepository<Persona, Long> {

    //Filtro de búsqueda de persona por su email
    Persona findByEmail(String email);

    //Filtro de búsqueda de personas por nombre
    List<Persona> findByNombreContainingIgnoreCase(String nombre);
}