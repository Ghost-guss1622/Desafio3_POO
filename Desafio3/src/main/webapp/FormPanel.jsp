<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="./Css/LoginStyle.css">
</head>
<body>

<form action="LoginServlet" method="post">

    <%
        String errorMessage = (String) session.getAttribute("error");
        if (errorMessage != null && !errorMessage.isEmpty()){
    %>
    <p style="color: red; text-align: center;"><%= errorMessage %></p>
    <%
            session.removeAttribute("error");
        }
    %>
    <div class="bloque">
        <h1><%= "Bienvenido Admin!" %></h1>

        <label for="username">Nombre de usuario</label>
        <input type="text" id="username" name="user" required>

        <label for="password">Contraseña</label>
        <input type="password" id="password" name="pass" required>

        <center><button type="submit">Iniciar Sesión</button> </center>
    </div>
    <br>
</form>
</body>
</html>