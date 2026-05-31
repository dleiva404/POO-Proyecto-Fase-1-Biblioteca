<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="es">
<head>
 <meta charset="UTF-8" />
 <meta name="viewport" content="width=device-width, initial-scale=1.0" />
 <title>Menu - Biblioteca</title>
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

<div class="container text-center" style="margin-top: 80px">
 <h2>Menú Principal</h2>
 <p class="text-white-50">Seleccione una opción para continuar</p>
</div>

<div class="container">
 <div class="row g-4 justify-content-center mt-4">

  <c:if test="${usuario.tipo_usuario == 'PROFESOR' or usuario.tipo_usuario == 'ADMINISTRADOR'}">
   <div class="col-md-3">
    <a href="Material.jsp" class="text-decoration-none">
     <div class="card text-center p-4"><h5 class="mt-2">Registrar Material</h5></div>
    </a>
   </div>
  </c:if>

  <div class="col-md-3">
   <a href="ConsultarMaterialServlet" class="text-decoration-none">
    <div class="card text-center p-4"><h5 class="mt-2">Consultar Inventario</h5></div>
   </a>
  </div>

  <div class="col-md-3">
   <a href="PrestamoServlet" class="text-decoration-none">
    <div class="card text-center p-4"><h5 class="mt-2">Realizar Préstamo</h5></div>
   </a>
  </div>

  <c:if test="${usuario.tipo_usuario == 'PROFESOR' or usuario.tipo_usuario == 'ADMINISTRADOR'}">
   <div class="col-md-3">
    <a href="DevolucionServlet" class="text-decoration-none">
     <div class="card text-center p-4"><h5 class="mt-2">Devoluciones</h5></div>
    </a>
   </div>
  </c:if>

  <c:if test="${usuario.tipo_usuario == 'PROFESOR' or usuario.tipo_usuario == 'ADMINISTRADOR'}">
   <div class="col-md-3">
    <a href="HistorialPrestamosServlet" class="text-decoration-none">
     <div class="card text-center p-4"><h5 class="mt-2">Historial de Préstamos</h5></div>
    </a>
   </div>
  </c:if>

  <c:if test="${usuario.tipo_usuario == 'ADMINISTRADOR'}">
   <div class="col-md-3">
    <a href="ConfiguracionMora.jsp" class="text-decoration-none">
     <div class="card text-center p-4"><h5 class="mt-2">Configuración Mora</h5></div>
    </a>
   </div>
  </c:if>

  <c:if test="${usuario.tipo_usuario == 'ADMINISTRADOR'}">
   <div class="col-md-3">
    <a href="UsuarioServlet" class="text-decoration-none">
     <div class="card text-center p-4"><h5 class="mt-2">Gestión de Usuarios</h5></div>
    </a>
   </div>
  </c:if>

 </div>
</div>
</body>
</html>