package Consultores.modelo;

import java.sql.Timestamp;

public class Solicitud {
    private int idSolicitud;
    private int idMascota;      // ID de la mascota que se quiere adoptar
    private int idPersona;       // ID de la persona que solicita (si tienes una tabla de personas)
    private Timestamp fechaSolicitud;   // Fecha y hora de la solicitud
    private String estado;

    public Solicitud() {}

    // Constructor
    public Solicitud(int idSolicitud, int idMascota, int idPersona,
                     Timestamp fechaSolicitud, String estado) {
        this.idSolicitud = idSolicitud;
        this.idMascota = idMascota;
        this.idPersona = idPersona;
        this.fechaSolicitud = fechaSolicitud;
        this.estado = estado;
    }

    public int getIdSolicitud() {
        return idSolicitud;
    }
    public void setIdSolicitud(int idSolicitud) {
        this.idSolicitud = idSolicitud;
    }
    public int getIdMascota() {
        return idMascota;
    }
    public void setIdMascota(int idMascota) {
        this.idMascota = idMascota;
    }
    public int getIdPersona() {
        return idPersona;
    }
    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }
    public Timestamp getFechaSolicitud() {
        return fechaSolicitud;
    }
    public void setFechaSolicitud(Timestamp fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }
   public String getEstado() {
        return estado;
   }
   public void setEstado(String estado) {
        this.estado = estado;
   }
}

