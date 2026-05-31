<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="biblioteca.modelo.MaterialBean" %>
<% List<MaterialBean> materiales = (List<MaterialBean>) request.getAttribute("materiales"); %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Materiales - Sistema de Biblioteca</title>
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
        }
        .container {
            max-width: 1200px;
            margin: 30px auto;
            padding: 0 20px;
        }
        .back-btn {
            background: #667eea;
            color: white;
            padding: 10px 20px;
            border-radius: 5px;
            text-decoration: none;
            display: inline-block;
            margin-bottom: 20px;
        }
        h2 {
            color: #333;
            margin-bottom: 20px;
        }
        .search-box {
            background: white;
            padding: 20px;
            border-radius: 10px;
            margin-bottom: 20px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }
        .search-box form {
            display: grid;
            grid-template-columns: 1fr 2fr 1fr;
            gap: 15px;
        }
        select, input, button {
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
            font-size: 14px;
        }
        button {
            background: #667eea;
            color: white;
            border: none;
            cursor: pointer;
        }
        button:hover {
            background: #764ba2;
        }
        .materials-list {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
            gap: 20px;
        }
        .material-card {
            background: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            transition: transform 0.3s;
        }
        .material-card:hover {
            transform: translateY(-5px);
        }
        .material-card h3 {
            color: #667eea;
            margin-bottom: 10px;
        }
        .material-type {
            background: #f0f0f0;
            padding: 5px 10px;
            border-radius: 20px;
            font-size: 12px;
            display: inline-block;
            margin-bottom: 10px;
        }
        .material-card p {
            color: #666;
            font-size: 14px;
            margin: 5px 0;
        }
        .disponible {
            color: green;
            font-weight: bold;
        }
        .ver-btn {
            background: #667eea;
            color: white;
            padding: 8px 15px;
            border-radius: 5px;
            text-decoration: none;
            display: inline-block;
            margin-top: 10px;
        }
        .ver-btn:hover {
            background: #764ba2;
        }
    </style>
</head>
<body>
    <div class="header">
        <h1>📚 Catálogo de Materiales</h1>
    </div>

    <div class="container">
        <a href="dashboard" class="back-btn">← Volver al Dashboard</a>
        
        <h2>Materiales Disponibles</h2>
        
        <div class="search-box">
            <form method="GET" action="material">
                <select name="criterio">
                    <option value="titulo">Por Título</option>
                    <option value="tipo">Por Tipo</option>
                    <option value="autor">Por Autor</option>
                </select>
                <input type="text" name="busqueda" placeholder="Ingresa tu búsqueda..." required>
                <button type="submit" name="accion" value="buscar">Buscar</button>
            </form>
        </div>

        <div class="materials-list">
            <% if (materiales != null && !materiales.isEmpty()) {
                for (MaterialBean material : materiales) {
            %>
                <div class="material-card">
                    <h3><%= material.getTitulo() %></h3>
                    <span class="material-type"><%= material.getTipo() %></span>
                    <p><strong>Ubicación:</strong> <%= material.getUbicacion() %></p>
                    <p class="disponible">✓ Disponibles: <%= material.getCantidadDisponible() %></p>
                    <a href="material?accion=ver&id=<%= material.getId() %>" class="ver-btn">Ver Detalles</a>
                </div>
            <% }
            } else { %>
                <p>No hay materiales disponibles en este momento.</p>
            <% } %>
        </div>
    </div>
</body>
</html>
