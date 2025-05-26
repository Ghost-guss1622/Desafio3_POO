<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Formulario de registro</title>
  <link rel="stylesheet" type="text/css" href="./Css/RegistroStyle.css">
</head>
<body>
<form action="/Registros" method="post">

  <div class="container">

    <h2>Formulario de registros.</h2>

    <label for="nombre">Ingrese sus Nombres </label>
    <input type="Text" id="nombre" name="nombre" required>
    <br>
    <br>

    <label for="correo">Ingrese su correo electrónico: </label>
    <input type="email" id="correo" name="correo" required>
    <br>
    <br>

    <label for="ciudad">Ingrese su ciudad de residencia: </label>
    <input type="text" id="ciudad" name="ciudad" required>
    <br>
    <br>


    <div class="btn-group">
      <button type="submit">Registrarse</button>
    </div>
  </div>
</form>

</body>
</html>