<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${empty usuario or (usuario.tipo_usuario != 'PROFESOR' and usuario.tipo_usuario != 'ADMINISTRADOR')}">
  <c:redirect url="Menu.jsp" />
</c:if>
<!doctype html>
<html lang="es">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Historial de Préstamos - Colegio Don Bosco</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" />
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

    <div class="container text-center" style="margin-top: 30px">
      <h2>Historial General de Préstamos</h2>
    </div>

    <div class="container d-flex flex-column flex-grow-1 pt-3 pb-5">
      <div class="table-responsive">
        <table class="table table-bordered table-hover bg-white align-middle">
          <thead class="table-dark">
            <tr>
              <th>ID</th>
              <th>Material</th>
              <th>Usuario</th>
              <th>Fecha Préstamo</th>
              <th>Fecha Devolución</th>
              <th>Estado</th>
              <th>Mora</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="p" items="${historial}">
              <tr>
                <td>${p.idPrestamo}</td>
                <td>${p.titulo}</td>
                <td>${p.nombre} ${p.apellido}</td>
                <td>${p.fechaPrestamo}</td>
                <td>${p.fechaDevolucion}</td>
                <td>${p.estado}</td>
                <td>$${p.mora}</td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </div>

      <div class="d-flex justify-content-end gap-3 mt-3">
        <a href="Menu.jsp" class="btn btn-volver text-decoration-none">Volver al Menú</a>
      </div>
    </div>
  </body>
</html>