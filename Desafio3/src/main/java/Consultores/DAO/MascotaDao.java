package Consultores.DAO;

import Consultores.ConnBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MascotaDao {
    public boolean actualizarEstadoMascota(int idMascota, String nuevoEstado) throws SQLException {
        String sql = "UPDATE mascota SET estado = ? WHERE id_mascota = ?";
        try (Connection conn = ConnBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nuevoEstado);
            stmt.setInt(2, idMascota);

            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;
        }
    }
}
