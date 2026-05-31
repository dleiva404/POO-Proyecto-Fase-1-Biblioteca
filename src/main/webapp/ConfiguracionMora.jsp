<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${empty usuario or usuario.tipo_usuario != 'ADMINISTRADOR'}">
  <c:redirect url="Menu.jsp" />
</c:if>
<!doctype html>
<html lang="es">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Configuración de Mora - Colegio Don Bosco</title>
  <link
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
          rel="stylesheet"
  />
  <link rel="stylesheet" href="css/styless.css" />
</head>
<body class="d-flex flex-column" style="min-height: 100vh">
<nav class="navbar px-4 d-flex justify-content-between">
  <span class="fw-bold text-white">Biblioteca - Colegio Amigos de Don Bosco</span>
  <div>
    <span class="text-white me-3">Hola, ${usuario.nombre}</span>
    <a href="LogoutServlet" class="btn btn-logout btn-sm fw-bold">Cerrar Sesión</a>
  </div>
</nav>

<div class="container flex-grow-1 d-flex align-items-center justify-content-center py-5">
  <div class="row justify-content-center w-100">
    <div class="col-lg-6">
      <div class="marco text-center">
        <h3 class="text-white mb-4">Configuración de Mora Diaria</h3>
        <form action="ConfiguracionMoraServlet" method="post">
          <div class="row align-items-center mb-4">
            <label class="col-sm-7 form-label lbl-der">Valor de recargo por día $:</label>
            <div class="col-sm-5">
              <input
                      type="number"
                      step="0.01"
                      min="0"
                      class="form-control"
                      name="valorMora"
                      required
              />
            </div>
          </div>
          <div class="d-flex justify-content-center gap-4 mt-4">
            <button type="submit" class="btn btn-guardar">Guardar Cambios</button>
            <a href="Menu.jsp" class="btn btn-volver text-decoration-none">Volver al Menú</a>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>
</body>
</html>