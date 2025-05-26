package com.desafio3.demo.Repository;

import com.desafio3.demo.Model.EstadoSolicitud;
import com.desafio3.demo.Model.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SolicitudRepository extends JpaRepository<Solicitud, Long> {
    List<Solicitud> findByEstado(EstadoSolicitud estado);
}