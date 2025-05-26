<%@ page import="Consultores.ObjDatos" %>
<%@ page import="Consultores.ConnBD" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.Connection" %>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Página inicial</title>
    <link rel="stylesheet" type="text/css" href="./Css/MascStyle.css">
</head>
<body>
<h1><%= "Lista de mascotas" %></h1>
<br/>
<div>
    <a class="btn-success btn-sm" href="FormPanel.jsp">Panel de administración</a>
    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Edad</th>
            <th>Tipo</th>
            <th>Descripción</th>
            <th>Estado</th>
            <th>Acción</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<ObjDatos> listaDatos = new ArrayList<>();
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;

            try {
                conn = ConnBD.getConnection();
                String consulta = "SELECT id_mascota, nombre, tipo, edad, descripcion, estado FROM mascota";
                stmt = conn.prepareStatement(consulta);
                rs = stmt.executeQuery();
                while (rs.next()) {
                    listaDatos.add(new ObjDatos(
                            rs.getInt("id_mascota"),
                            rs.getString("nombre"),
                            rs.getInt("edad"),
                            rs.getString("tipo"),
                            rs.getString("descripcion"),
                            rs.getString("estado")
                    ));
                }

                if (listaDatos.isEmpty()) {
        %>
        <tr><td colspan="7">No hay ningún registro disponible.</td></tr>
        <%
        } else {
            for (ObjDatos mascota : listaDatos) {
        %>
        <tr>
            <td><%= mascota.getId() %></td>
            <td><%= mascota.getNombre() %></td>
            <td><%= mascota.getEdad() %></td>
            <td><%= mascota.getTipo() %></td>
            <td><%= mascota.getDescripcion() %></td>
            <td><%= mascota.getEstado() %></td>
            <td>
                <%
                    String estadoMascota = mascota.getEstado();

                    if ("Disponible".equalsIgnoreCase(estadoMascota)) {
                %>
                <a href="FormRegistro.jsp?mascotaId=<%= mascota.getId() %>" class="btn btn-primary">Solicitar</a>
                <%
                } else if ("En Proceso".equalsIgnoreCase(estadoMascota)) {
                %>
                <button class="btn btn-warning" disabled style="background-color: #dc3545; color: #F5F5F5; cursor: not-allowed;
                opacity: 0.90; border: none">En Proceso</button>
                <%
                } else {
                %>
                <button class="btn btn-secondary" disabled>No Disponible</button>
                <%
                    }
                %>
            </td>
        </tr>
        <%
                    }
                }
            } catch (Exception e) {
                System.out.println("Error al cargar datos: " + e.getMessage());
            } finally {
                if (rs != null) try { rs.close(); } catch (Exception ignored) {}
                if (stmt != null) try { stmt.close(); } catch (Exception ignored) {}
                if (conn != null) try { conn.close(); } catch (Exception ignored) {}
            }
        %>
        </tbody>
    </table>
</div>
</body>
</html>