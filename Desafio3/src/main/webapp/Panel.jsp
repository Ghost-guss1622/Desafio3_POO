<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="Consultores.modelo.Solicitud" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Panel de Administración</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/Css/AdminStyle.css">
</head>
<body>
<div class="log">
    <a href="index.jsp">Cerrar Sesión</a>
</div>

<h1>Panel de Administración</h1>

<%
    String mensaje = request.getParameter("mensaje");
    if (mensaje != null && !mensaje.isEmpty()) {
%>
<p class="message"><%= mensaje %></p>
<%
    }
    String error = request.getParameter("error");
    if (error != null && !error.isEmpty()) {
%>
<p class="error"><%= error %></p>
<%
    }
%>

<h2>Listado de Solicitudes de Adopción</h2>
<table>
    <thead>
    <tr>
        <th>ID Solicitud</th>
        <th>ID Mascota</th>
        <th>Nombre Solicitante</th>
        <th>Email Solicitante</th>
        <th>Teléfono Solicitante</th>
        <th>Fecha Solicitud</th>
        <th>Estado</th>
        <th>Acciones</th>
    </tr>
    </thead>
    <tbody>
    <%
        // Obtener las solicitudes
        List<Solicitud> listaSolicitudes = (List<Solicitud>) request.getAttribute("listaSolicitudes");

        if (listaSolicitudes == null || listaSolicitudes.isEmpty()) {
    %>
    <tr>
        <td colspan="8" style="text-align: center;">No hay solicitudes de adopción pendientes.</td>
    </tr>
    <%
    } else {
        for (Solicitud solicitud : listaSolicitudes) {
    %>
    <tr>
        <td><%= solicitud.getIdSolicitud() %></td>
        <td><%= solicitud.getIdMascota() %></td>
        <td><%= solicitud.getFechaSolicitud() %></td>
        <td><%= solicitud.getEstado() %></td>
        <td>
            <%
                if ("Pendiente".equals(solicitud.getEstado())) {
            %>
            <form action="<%= request.getContextPath() %>/admin/solicitudes/actualizarEstado" method="post">
                <input type="hidden" name="idSolicitud" value="<%= solicitud.getIdSolicitud() %>"/>
                <input type="hidden" name="nuevoEstado" value="Aprobada"/>
                <button type="submit" class="approve">Aprobar</button>
            </form>

            <form action="<%= request.getContextPath() %>/admin/solicitudes/actualizarEstado" method="post">
                <input type="hidden" name="idSolicitud" value="<%= solicitud.getIdSolicitud() %>"/>
                <input type="hidden" name="nuevoEstado" value="Rechazada"/>
                <button type="submit" class="reject">Rechazar</button>
            </form>
            <%
            } else {
            %>
            <span><%= solicitud.getEstado() %></span>
            <%
                }
            %>
        </td>
    </tr>
    <%
            }
        }
    %>
    </tbody>
</table>

<hr>

<h2>Cambiar Estado de una Mascota</h2>
<form action="<%= request.getContextPath() %>/cambiarEstadoMascota" method="post"> <%-- ¡Cambiamos la URL aquí! --%>
    <label for="mascotaIdEstado">ID de la Mascota:</label>
    <input type="number" id="mascotaIdEstado" name="mascotaId" required><br><br>

    <label for="nuevoEstadoMascota">Nuevo Estado:</label>
    <select id="nuevoEstadoMascota" name="nuevoEstado">
        <option value="Disponible">Disponible</option>
        <option value="Adoptado">Adoptado</option>
        <option value="En Proceso">En Proceso</option>
    </select><br><br>

    <button type="submit" class="submit-mascota">Actualizar Estado de Mascota</button>
</form>

</body>
</html>