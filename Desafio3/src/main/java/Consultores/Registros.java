package Consultores;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/Registros")
public class Registros extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String email = request.getParameter("correo");
        String ciudad = request.getParameter("ciudad");
        HttpSession session = request.getSession();

        //Ejeución de la consulta
        try {
            Connection conn = ConnBD.getConnection();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO persona (nombre, correo, ciudad) VALUES (?,?,?)");
            stmt.setString(1, nombre);
            stmt.setString(2, email);
            stmt.setString(3, ciudad);

            int filasAfectadas = stmt.executeUpdate();

            //Validacion para registrar los datos del usuario en la Base de Datos
            if (filasAfectadas > 0) {
                response.setContentType("text/html");
                response.getWriter().println("<script type=\"text/javascript\">");
                response.getWriter().println("alert('El usuario se ha registrado con exito!');");
                response.getWriter().println("window.location.href='index.jsp';");
                response.getWriter().println("</script>");
            } else {
                response.getWriter().println("Error al registrar el usuario");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            e.printStackTrace(response.getWriter());
        }
    }
}