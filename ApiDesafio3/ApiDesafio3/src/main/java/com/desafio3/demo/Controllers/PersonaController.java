package com.desafio3.demo.Controllers;

import com.desafio3.demo.Model.Persona;
import com.desafio3.demo.Repository.PersonaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/personas")
public class PersonaController {

    @Autowired
    private PersonaRepository personaRepository;

    @GetMapping
    public ResponseEntity<List<Persona>> getAllPersonas() {
        List<Persona> personas = personaRepository.findAll();
        return new ResponseEntity<>(personas, HttpStatus.OK);
    }

    //Obtener una persona por ID
    @GetMapping("/{id}")
    public ResponseEntity<Persona> getPersonaById(@PathVariable Long id) {
        Optional<Persona> persona = personaRepository.findById(id);
        return persona.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Persona> createPersona(@Valid @RequestBody Persona persona) {
        Persona savedPersona = personaRepository.save(persona);
        return new ResponseEntity<>(savedPersona, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Persona> updatePersona(@PathVariable Long id, @Valid @RequestBody Persona personaDetails) {
        Optional<Persona> optionalPersona = personaRepository.findById(id);
        if (optionalPersona.isPresent()) {
            Persona persona = optionalPersona.get();
            persona.setNombre(personaDetails.getNombre());
            persona.setEmail(personaDetails.getEmail());

            Persona updatedPersona = personaRepository.save(persona);
            return new ResponseEntity<>(updatedPersona, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletePersona(@PathVariable Long id) {
        try {
            personaRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Persona>> buscarPersonasPorNombre(@RequestParam String nombre) {
        List<Persona> personas = personaRepository.findByNombreContainingIgnoreCase(nombre); // Usa el método que descomentaste
        if (personas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(personas, HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Persona> getPersonaByEmail(@PathVariable String email) {
        Persona persona = personaRepository.findByEmail(email);
        if (persona != null) {
            return new ResponseEntity<>(persona, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}