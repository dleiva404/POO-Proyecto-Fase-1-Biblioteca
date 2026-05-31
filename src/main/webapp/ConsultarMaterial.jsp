<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${empty usuario}">
  <c:redirect url="Login.jsp" />
</c:if>
<!doctype html>
<html lang="es">
 <head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Consulta de Materiales - Colegio Don Bosco</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styless.css" />
 </head>
 <body class="d-flex flex-column" style="min-height: 100vh">
  <nav class="navbar px-4 d-flex justify-content-between">
   <span class="fw-bold text-white">Biblioteca - Colegio Amigos de Don Bosco</span>
   <div>
    <span class="text-white me-3">Hola, ${usuario.nombre}</span>
    <a href="${pageContext.request.contextPath}/logout" class="btn btn-logout btn-sm fw-bold">Cerrar Sesión</a>
   </div>
  </nav>

  <div class="container text-center" style="margin-top: 30px">
   <h2>Consulta de Materiales</h2>
  </div>

  <div class="container d-flex flex-column flex-grow-1 pt-3 pb-5">
   <form action="ConsultarMaterialServlet" method="get">
    <input type="hidden" name="accion" value="buscar">
    <div class="row justify-content-center align-items-center mb-4">
     <label class="col-auto form-label">Buscar por título o código:</label>
     <div class="col-md-4">
      <input type="text" class="form-control" name="texto" placeholder="Escriba para buscar..." value="${texto}" />
     </div>
     <div class="col-auto">
      <button type="submit" class="btn btn-guardar">Realizar Búsqueda</button>
     </div>
    </div>

    <div class="table-responsive">
     <table class="table table-bordered table-hover bg-white align-middle">
      <thead class="table-dark">
       <tr>
        <th>Código</th>
        <th>Título</th>
        <th>Tipo</th>
        <th>Ubicación</th>
        <th>Stock</th>
        <th>Disponible</th>
       </tr>
      </thead>
      <tbody>
       <c:forEach var="m" items="${lista}">
        <tr>
         <td>${m.codigo}</td>
         <td>${m.titulo}</td>
         <td>${m.tipo_material}</td>
         <td>${m.ubicacion}</td>
         <td>${m.cantidad_total}</td>
         <td>${m.cantidad_disponible}</td>
        </tr>
       </c:forEach>
      </tbody>
     </table>
    </div>

    <div class="d-flex justify-content-end gap-3 mt-3">
     <a href="Menu.jsp" class="btn btn-volver text-decoration-none">Volver al Menú</a>
    </div>
   </form>
  </div>
 </body>
</html>