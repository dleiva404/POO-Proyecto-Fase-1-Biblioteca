<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="es">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Login - Biblioteca</title>
  <link
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
          rel="stylesheet"
  />
  <link rel="stylesheet" href="css/styless.css" />
</head>
<body class="d-flex justify-content-center align-items-center vh-100">
<div class="card-login p-4" style="width: 400px">
  <div class="text-center mb-3">
    <img src="img/logo.png" alt="Logo" />
    <h2 class="mt-2">Sistema de Biblioteca</h2>
  </div>
  <form action="LoginServlet" method="post">
    <div class="d-flex align-items-center mb-3">
      <label style="width: 120px; min-width: 120px">Carnet</label>
      <input type="text" class="form-control" name="carnet" required />
    </div>
    <div class="d-flex align-items-center mb-3">
      <label style="width: 120px; min-width: 120px">Contraseña</label>
      <input type="password" class="form-control" name="password" required />
    </div>
    <button type="submit" class="btn btn-warning w-100 fw-bold">
      Iniciar Sesión
    </button>
    <a href="Registro.jsp" class="text-decoration-none">
      <h6>¿Usuario nuevo? Regístrate aquí</h6>
    </a>
    <a href="RestablecerContraseña.jsp" class="text-decoration-none">
      <h6>Olvidé mi contraseña</h6>
    </a>
  </form>
</div>
</body>
</html>