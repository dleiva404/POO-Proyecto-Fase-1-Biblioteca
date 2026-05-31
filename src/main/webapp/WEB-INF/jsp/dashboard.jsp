<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="biblioteca.modelo.UsuarioBean" %>
<% UsuarioBean usuario = (UsuarioBean) session.getAttribute("usuario"); %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard - Sistema de Biblioteca</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: #f5f5f5;
        }
        .header {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            padding: 20px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        .header h1 {
            font-size: 24px;
        }
        .user-info {
            display: flex;
            align-items: center;
            gap: 15px;
        }
        .logout-btn {
            background: rgba(255, 255, 255, 0.2);
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
            text-decoration: none;
        }
        .logout-btn:hover {
            background: rgba(255, 255, 255, 0.3);
        }
        .container {
            max-width: 1200px;
            margin: 30px auto;
            padding: 0 20px;
        }
        .welcome {
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            margin-bottom: 30px;
        }
        .welcome h2 {
            color: #333;
            margin-bottom: 10px;
        }
        .welcome p {
            color: #666;
            font-size: 16px;
        }
        .menu-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
            gap: 20px;
        }
        .menu-card {
            background: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            text-align: center;
            transition: transform 0.3s;
        }
        .menu-card:hover {
            transform: translateY(-5px);
        }
        .menu-card h3 {
            color: #667eea;
            margin-bottom: 10px;
        }
        .menu-card p {
            color: #666;
            font-size: 14px;
            margin-bottom: 15px;
        }
        .menu-card a {
            background: #667eea;
            color: white;
            padding: 10px 20px;
            border-radius: 5px;
            text-decoration: none;
            display: inline-block;
        }
        .menu-card a:hover {
            background: #764ba2;
        }
    </style>
</head>
<body>
    <div class="header">
        <h1>📚 Sistema de Biblioteca</h1>
        <div class="user-info">
            <span><%= usuario.getNombre() %> <%= usuario.getApellido() %></span>
            <span style="font-size: 12px; opacity: 0.8;">(<%= usuario.getTipoUsuario() %>)</span>
            <a href="logout" class="logout-btn">Cerrar Sesión</a>
        </div>
    </div>

    <div class="container">
        <div class="welcome">
            <h2>Bienvenido, <%= usuario.getNombre() %>!</h2>
            <p>Selecciona una opción del menú para continuar.</p>
        </div>

        <div class="menu-grid">
            <div class="menu-card">
                <h3>📚 Consultar Materiales</h3>
                <p>Explora el catálogo de libros, revistas, CD y otros materiales disponibles.</p>
                <a href="material?accion=listar">Ver Materiales</a>
            </div>

            <div class="menu-card">
                <h3>📖 Mis Préstamos</h3>
                <p>Revisa los materiales que actualmente tienes en préstamo.</p>
                <a href="prestamo?accion=misPrestamos">Ver Préstamos</a>
            </div>

            <div class="menu-card">
                <h3>↩️ Devolver Material</h3>
                <p>Registra la devolución de materiales que ya no necesitas.</p>
                <a href="devolucion?accion=nueva">Devolver</a>
            </div>

            <div class="menu-card">
                <h3>⚙️ Mi Perfil</h3>
                <p>Consulta y actualiza tu información personal y datos de contacto.</p>
                <a href="usuario?accion=perfil">Ver Perfil</a>
            </div>
        </div>
    </div>
</body>
</html>
