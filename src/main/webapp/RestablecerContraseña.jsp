<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="es">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Restablecer Contraseña - Biblioteca</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="css/styless.css" />
  </head>
  <body class="d-flex justify-content-center align-items-center" style="min-height: 100vh">
    <div class="card-login p-4" style="width: 450px">
      <div class="text-center mb-4">
        <h2>Cambio de Contraseña</h2>
      </div>
      <form action="RestablecerServlet" method="post">
        <div class="d-flex align-items-center mb-3">
          <label style="width: 170px; min-width: 170px">Número de Carnet</label>
          <input type="text" class="form-control" name="carnet" required />
        </div>
        <div class="d-flex align-items-center mb-3">
          <label style="width: 170px; min-width: 170px">Correo Electrónico</label>
          <input type="email" class="form-control" name="correo" required />
        </div>
        <div class="d-flex align-items-center mb-3">
          <label style="width: 170px; min-width: 170px">Nueva Contraseña</label>
          <input type="password" class="form-control" name="nuevaPassword" minlength="4" required />
        </div>
        <div class="d-flex align-items-center mb-4">
          <label style="width: 170px; min-width: 170px">Confirmar Contraseña</label>
          <input type="password" class="form-control" name="confirmPassword" minlength="4" required />
        </div>
        <div class="d-flex gap-3">
          <a href="Login.jsp" class="btn btn-volver text-decoration-none">Volver al Login</a>
          <button type="submit" class="btn btn-actualizar w-50 fw-bold">Actualizar</button>
        </div>
      </form>
    </div>
  </body>
</html>