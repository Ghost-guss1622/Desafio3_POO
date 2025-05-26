package com.desafio3.demo.Repository;

import com.desafio3.demo.Model.EstadoMascota;
import com.desafio3.demo.Model.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MascotaRepository extends JpaRepository<Mascota, Long> {

    // Ejemplo de consulta personalizada: obtener mascotas por estado
    List<Mascota> findByEstado(EstadoMascota estado);

    // Puedes agregar más métodos si lo necesitas, por ejemplo:
    List<Mascota> findByTipo(String tipo);
}
