package Consultores;

import Consultores.DAO.MascotaDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/cambiarEstadoMascota") // ¡Cambiamos la URL del mapeo aquí!
public class CambiarEstado extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private MascotaDao mascotaDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        mascotaDAO = new MascotaDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStr = request.getParameter("mascotaId");
        String nuevoEstado = request.getParameter("nuevoEstado");

        try {
            int idMascota = Integer.parseInt(idStr);

            boolean actualizado = mascotaDAO.actualizarEstadoMascota(idMascota, nuevoEstado);

            if (actualizado) {

                response.sendRedirect(request.getContextPath() + "/Panel.jsp?mensaje=Estado actualizado correctamente");
            } else {
                response.sendRedirect(request.getContextPath() + "/Panel.jsp?error=No se encontró la mascota con ese ID o el estado ya era el mismo.");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/Panel.jsp?error=ID de mascota inválido.");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/Panel.jsp?error=Error al actualizar estado: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/Panel.jsp?error=Ocurrió un error inesperado.");
        }
    }
}