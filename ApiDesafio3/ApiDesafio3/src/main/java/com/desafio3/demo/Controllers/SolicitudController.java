package com.desafio3.demo.Controllers;

import com.desafio3.demo.Model.*;
import com.desafio3.demo.Repository.MascotaRepository;
import com.desafio3.demo.Repository.PersonaRepository;
import com.desafio3.demo.Repository.SolicitudRepository;
import com.desafio3.demo.Model.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/solicitudes")
public class SolicitudController {

    @Autowired
    private SolicitudRepository solicitudRepository;
    @Autowired
    private MascotaRepository mascotaRepository;
    @Autowired
    private PersonaRepository personaRepository;

    // Endpoint: POST /api/solicitudes
    // Funcionalidad: Crear una solicitud de adopción.
    // Espera un JSON con {"mascotaId": 1, "personaId": 2}
    @PostMapping
    public ResponseEntity<Solicitud> createSolicitud(@RequestBody Map<String, Long> payload) {
        Long mascotaId = payload.get("mascotaId");
        Long personaId = payload.get("personaId");

        Optional<Mascota> mascotaOpt = mascotaRepository.findById(mascotaId);
        Optional<Persona> personaOpt = personaRepository.findById(personaId);

        if (mascotaOpt.isEmpty() || personaOpt.isEmpty()) {
            return ResponseEntity.badRequest().body(null); // 400 Bad Request si no existe Mascota o Persona
        }

        Mascota mascota = mascotaOpt.get();
        Persona persona = personaOpt.get();

        // Verificar si la mascota está disponible
        if (mascota.getEstado() != EstadoMascota.DISPONIBLE) {
            // Si la mascota no está disponible, no se puede crear la solicitud
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null); // 409 Conflict
        }

        Solicitud solicitud = new Solicitud();
        solicitud.setMascota(mascota);
        solicitud.setSolicitante(persona);
        // fechaSolicitud y estado (PENDIENTE) se configuran automáticamente en la entidad Solicitud con @PrePersist

        Solicitud savedSolicitud = solicitudRepository.save(solicitud);

        // Opcional: Una vez que se crea una solicitud, podrías cambiar el estado de la mascota a PENDIENTE
        // para que no puedan enviarse más solicitudes para la misma mascota mientras está en proceso.
        // mascota.setEstado(EstadoMascota.PENDIENTE);
        // mascotaRepository.save(mascota);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedSolicitud); // 201 Created
    }

    // Endpoint: GET /api/solicitudes
    // Funcionalidad: Ver todas las solicitudes.
    // Puedes añadir un parámetro 'estado' para filtrar: GET /api/solicitudes?estado=PENDIENTE
    @GetMapping
    public List<Solicitud> getAllSolicitudes(@RequestParam(required = false) String estado) {
        if (estado != null && !estado.isEmpty()) {
            try {
                EstadoSolicitud estadoEnum = EstadoSolicitud.valueOf(estado.toUpperCase());
                return solicitudRepository.findByEstado(estadoEnum); // Necesitas este método en SolicitudRepository
            } catch (IllegalArgumentException e) {
                // Manejar estado inválido o simplemente devolver todas
                return solicitudRepository.findAll();
            }
        }
        return solicitudRepository.findAll();
    }

    // Endpoint: PUT /api/solicitudes/{id}
    // Funcionalidad: Cambiar estado de una solicitud (para el administrador).
    // Espera un JSON con {"estado": "APROBADA"} o {"estado": "RECHAZADA"}
    @PutMapping("/{id}")
    public ResponseEntity<Solicitud> updateSolicitudEstado(@PathVariable Long id, @RequestBody Map<String, String> payload) {
        Optional<Solicitud> solicitudOpt = solicitudRepository.findById(id);

        if (solicitudOpt.isEmpty()) {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }

        Solicitud solicitud = solicitudOpt.get();
        String nuevoEstadoStr = payload.get("estado");

        if (nuevoEstadoStr == null || nuevoEstadoStr.isEmpty()) {
            return ResponseEntity.badRequest().build(); // 400 Bad Request
        }

        try {
            EstadoSolicitud nuevoEstado = EstadoSolicitud.valueOf(nuevoEstadoStr.toUpperCase());
            solicitud.setEstado(nuevoEstado);

            // Lógica para actualizar el estado de la mascota basada en el estado de la solicitud
            if (nuevoEstado == EstadoSolicitud.APROBADA) {
                Mascota mascota = solicitud.getMascota();
                if (mascota != null && mascota.getEstado() != EstadoMascota.ADOPTADO) {
                    mascota.setEstado(EstadoMascota.ADOPTADO);
                    mascotaRepository.save(mascota);
                }
            } else if (nuevoEstado == EstadoSolicitud.RECHAZADA) {
                Mascota mascota = solicitud.getMascota();
                // Si la solicitud es rechazada y la mascota no está ya Adoptado, regresa a Disponible
                if (mascota != null && mascota.getEstado() != EstadoMascota.ADOPTADO) {
                    mascota.setEstado(EstadoMascota.DISPONIBLE);
                    mascotaRepository.save(mascota);
                }
            }

            Solicitud updatedSolicitud = solicitudRepository.save(solicitud);
            return ResponseEntity.ok(updatedSolicitud); // 200 OK
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null); // 400 Bad Request si el estado es inválido
        }
    }

    // Opcional: DELETE /api/solicitudes/{id} para eliminar una solicitud
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSolicitud(@PathVariable Long id) {
        Optional<Solicitud> solicitud = solicitudRepository.findById(id);
        if (solicitud.isPresent()) {
            solicitudRepository.delete(solicitud.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}