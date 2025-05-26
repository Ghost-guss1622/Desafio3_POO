package Consultores;

import java.io.IOException;
import java.sql.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/LoginServlet")

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        resp.sendRedirect("Panel.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String usuario = request.getParameter("user");
        String Clave = request.getParameter("pass");

        String url = "jdbc:mysql://localhost:3306/adopcion_mascotas?useSSL=false&serverTimezone=UTC";
        String user = "root";
        String password = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);

            // Consulta para verificar usuario y contraseña
            String query = "SELECT * FROM administracion WHERE usuario = ? AND clave = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, usuario);
            stmt.setString(2, Clave);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                //Validacion de datos
                request.getSession().setAttribute("usuario", usuario);
                response.sendRedirect("Panel.jsp");
            } else {
                request.getSession().setAttribute("error", "Usuario o contraseña incorrectas!");
                response.sendRedirect("FormPanel.jsp");
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            request.getSession().setAttribute("error", "Error al conectar con la base de datos.");
            response.sendRedirect("FormPanel.jsp");
        }
    }
}
