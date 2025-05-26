package com.desafio3.demo.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "mascota")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mascota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private int edad;
    private String tipo;
    private String descripcion;

    @Enumerated(EnumType.STRING)
    private EstadoMascota estado; //Enum del EstadoMascota

    public Mascota(String nombre, int edad, String tipo, String descripcion, EstadoMascota estado) {
        this.nombre = nombre;
        this.edad = edad;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.estado = estado;
    }
}