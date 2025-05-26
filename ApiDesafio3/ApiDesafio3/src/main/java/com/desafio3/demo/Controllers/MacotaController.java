package com.desafio3.demo.Controllers;

import com.desafio3.demo.Model.EstadoMascota;
import com.desafio3.demo.Model.Mascota;
import com.desafio3.demo.Repository.MascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/mascotas")
public class MacotaController {
    @Autowired // Inyecta el repositorio de Mascota
    private MascotaRepository mascotaRepository;

    //Endpoint para listar todas las mascotas filtrado mediante el estado.
    @GetMapping
    public List<Mascota> getAllMascotas(@RequestParam(required = false) String estado) {
        if (estado != null && !estado.isEmpty()) {
            try {
                EstadoMascota estadoEnum = EstadoMascota.valueOf(estado.toUpperCase());
                return mascotaRepository.findByEstado(estadoEnum);
            } catch (IllegalArgumentException e) {
                // Si el estado en el parámetro es inválido, podrías lanzar una excepción o simplemente devolver todas
                return mascotaRepository.findAll();
            }
        }
        return mascotaRepository.findAll();
    }

    //Registro de una nueva mascota.
    @PostMapping
    public Mascota createMascota(@RequestBody Mascota mascota) {
        return mascotaRepository.save(mascota);
    }

    //Cambiar estado de la mascota (por ejemplo, a adoptado).
    @PutMapping("/{id}/estado")
    public ResponseEntity<Mascota> updateMascotaEstado(@PathVariable Long id, @RequestBody String nuevoEstado) {
        Optional<Mascota> mascota = mascotaRepository.findById(id);
        if (mascota.isPresent()) {
            try {
                Mascota existingMascota = mascota.get();
                EstadoMascota estadoEnum = EstadoMascota.valueOf(nuevoEstado.toUpperCase());
                existingMascota.setEstado(estadoEnum);
                return ResponseEntity.ok(mascotaRepository.save(existingMascota));
            } catch (IllegalArgumentException e) {
                return ResponseEntity.badRequest().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mascota> getMascotaById(@PathVariable Long id) {
        Optional<Mascota> mascota = mascotaRepository.findById(id);
        return mascota.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMascota(@PathVariable Long id) {
        Optional<Mascota> mascota = mascotaRepository.findById(id);
        if (mascota.isPresent()) {
            mascotaRepository.delete(mascota.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
