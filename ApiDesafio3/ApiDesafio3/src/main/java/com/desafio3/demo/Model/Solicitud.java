package com.desafio3.demo.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "solicitudes")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Solicitud {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "persona_id", nullable = false)
    private Persona solicitante;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mascota_id", nullable = false) // Columna en la tabla 'solicitudes' que referencia el ID de la Mascota
    private Mascota mascota;

    @Column(nullable = false)
    private LocalDateTime fechaSolicitud;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoSolicitud estado;

    public Solicitud(Persona solicitante, Mascota mascota, EstadoSolicitud estado) {
        this.solicitante = solicitante;
        this.mascota = mascota;
        this.fechaSolicitud = LocalDateTime.now();
        this.estado = estado;
    }
}