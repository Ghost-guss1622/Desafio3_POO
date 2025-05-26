package Consultores;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/DatosListados")
public class DatosListados extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ObjDatos> listaDatos = new ArrayList<>();

        try {
            Connection conn = ConnBD.getConnection();
            String consulta = "select id_mascota, nombre, tipo, edad, descripcion, estado from mascota";
            PreparedStatement stmt = conn.prepareStatement(consulta);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                listaDatos.add(new ObjDatos(rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6)
                ));
            }
            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().println("Hubo un error en la consulta");
        }
        finally {
            req.getSession().setAttribute("DatosListados", listaDatos);
            resp.sendRedirect("index.jsp");
        }

    }
}
