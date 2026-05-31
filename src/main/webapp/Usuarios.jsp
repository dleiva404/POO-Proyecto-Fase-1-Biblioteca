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
    <title>Gestión de Usuarios - Colegio Don Bosco</title>
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
      <h2>Gestión de Usuarios</h2>
    </div>

    <div class="container d-flex flex-column flex-grow-1 pt-3 pb-5">
      <div class="table-responsive">
        <table class="table table-bordered table-hover bg-white align-middle">
          <thead class="table-dark">
            <tr>
              <th>ID</th>
              <th>Carnet</th>
              <th>Nombre</th>
              <th>Apellido</th>
              <th>Correo</th>
              <th>Rol</th>
              <th>Acciones</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="u" items="${usuarios}">
              <tr>
                <td>${u.idUsuario}</td>
                <td>${u.carnet}</td>
                <td>${u.nombre}</td>
                <td>${u.apellido}</td>
                <td>${u.correo}</td>
                <td>${u.tipoUsuario}</td>
                <td>
                  <div class="d-flex gap-2 justify-content-center">
                    <form action="UsuariosServlet" method="post" class="m-0 d-flex gap-2 align-items-center">
                      <input type="hidden" name="accion" value="cambiarRol" />
                      <input type="hidden" name="idUsuario" value="${u.idUsuario}" />
                      <select name="nuevoRol" class="form-select form-select-sm" style="width: auto">
                        <option ${u.tipoUsuario == 'ALUMNO' ? 'selected' : ''}>ALUMNO</option>
                        <option ${u.tipoUsuario == 'PROFESOR' ? 'selected' : ''}>PROFESOR</option>
                        <option ${u.tipoUsuario == 'ADMINISTRADOR' ? 'selected' : ''}>ADMINISTRADOR</option>
                      </select>
                      <button type="submit" class="btn btn-guardar btn-sm">Cambiar Rol</button>
                    </form>
                    <form action="UsuariosServlet" method="post" class="m-0">
                      <input type="hidden" name="accion" value="eliminar" />
                      <input type="hidden" name="idUsuario" value="${u.idUsuario}" />
                      <button type="submit" class="btn btn-danger btn-sm">Eliminar</button>
                    </form>
                  </div>
                </td>
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