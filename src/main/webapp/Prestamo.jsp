<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${empty usuario}">
 <c:redirect url="login" />
</c:if>
<!doctype html>
<html lang="es">
 <head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Gestión de Préstamos - Colegio Don Bosco</title>
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
   <h2>Gestión de Préstamos</h2>
  </div>

  <div class="container d-flex flex-column flex-grow-1 pt-3 pb-5">
   <form action="${pageContext.request.contextPath}/prestamos" method="get">
    <input type="hidden" name="accion" value="buscar">
    <div class="row justify-content-center align-items-center mb-3">
     <label class="col-auto form-label">Buscar Material (Título/Código):</label>
     <div class="col-md-4">
      <input type="text" class="form-control" name="texto" placeholder="Escriba para buscar..." value="${texto}" />
     </div>
     <div class="col-auto">
      <button type="submit" class="btn btn-guardar">Buscar</button>
     </div>
    </div>
   </form>

   <p class="text-white text-center fw-bold mb-3">
    Usuario: ${usuario.nombre} &nbsp;|&nbsp; Préstamos activos: ${activos}/${limite}
   </p>

   <div class="table-responsive">
    <table class="table table-bordered table-hover bg-white align-middle">
     <thead class="table-dark">
      <tr>
       <th>ID</th>
       <th>Código</th>
       <th>Título</th>
       <th>Tipo</th>
       <th>Disponibles</th>
       <th>Acción</th>
      </tr>
     </thead>
     <tbody>
      <c:forEach var="m" items="${materiales}">
       <tr>
        <td>${m.id_material}</td>
        <td>${m.codigo}</td>
        <td>${m.titulo}</td>
        <td>${m.tipo_material}</td>
        <td>${m.cantidad_disponible}</td>
        <td>
         <c:choose>
          <c:when test="${m.cantidad_disponible > 0}">
           <form action="${pageContext.request.contextPath}/prestamos" method="post" class="m-0">
            <input type="hidden" name="id_material" value="${m.id_material}" />
            <button type="submit" class="btn btn-guardar btn-sm">Prestar</button>
           </form>
          </c:when>
          <c:otherwise>
           <button type="button" class="btn btn-secondary btn-sm" disabled>Sin stock</button>
          </c:otherwise>
         </c:choose>
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