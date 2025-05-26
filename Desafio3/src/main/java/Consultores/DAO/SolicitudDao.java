package Consultores.DAO;

import Consultores.ConnBD;
import Consultores.modelo.Solicitud;

import java.sql.*;

public class SolicitudDao {

    public boolean guardarSolicitud(Solicitud solicitud) throws SQLException {
        String sql = "INSERT INTO solicitudes (id_mascota, id_persona, fecha_solicitud, estado) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConnBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, solicitud.getIdMascota());
            stmt.setInt(2, solicitud.getIdPersona()); // Usar el ID de persona de la sesión
            stmt.setTimestamp(3, solicitud.getFechaSolicitud()); // Fecha y hora actuales
            stmt.setString(4, "Pendiente"); // Estado inicial por defecto

            int filasAfectadas = stmt.executeUpdate();

            if (filasAfectadas > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        solicitud.setIdSolicitud(rs.getInt(1));
                    }
                }
                return true;
            }
        }
        return false;
    }
}
