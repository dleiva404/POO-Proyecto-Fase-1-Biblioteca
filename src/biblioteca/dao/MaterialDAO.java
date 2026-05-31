package biblioteca.dao;

import biblioteca.db.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MaterialDAO {

    public int insertarMaterial(String codigo, String titulo, String categoria, String ubicacion, int total, int disponible, String tipo) {
        String sql = "INSERT INTO materiales(codigo, titulo, categoria, ubicacion, cantidad_total, cantidad_disponible, tipo_material) VALUES (?, ?, ?, ?, ?, ?, ?)";
        int idGenerado = -1;

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, codigo);
            stmt.setString(2, titulo);
            stmt.setString(3, categoria);
            stmt.setString(4, ubicacion);
            stmt.setInt(5, total);
            stmt.setInt(6, disponible);
            stmt.setString(7, tipo);

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                idGenerado = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Error al guardar material: " + e.getMessage());
        }
        return idGenerado;
    }

    public void actualizarCodigo(int idMaterial, String codigo) {
        String sql = "UPDATE materiales SET codigo = ? WHERE id_material = ?";
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, codigo);
            ps.setInt(2, idMaterial);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar código: " + e.getMessage());
        }
    }

    public void guardarDetallesLibro(int idMaterial, String autor, String editorial, int numPaginas, String isbn, int anioPublicacion, String edicion, String tipoPasta, String idioma, String genero) {
        String sql = "INSERT INTO libros(id_material, autor, editorial, num_paginas, isbn, anio_publicacion, edicion, tipo_pasta, idioma, genero) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        ejecutarUpdate(sql, idMaterial, autor, editorial, numPaginas, isbn, anioPublicacion, edicion, tipoPasta, idioma, genero);
    }

    public void guardarDetallesObra(int idMaterial, String autor, String editorial, int numPaginas, String edicion, String tipoPasta, String genero, String idioma, String isbn, int anioPublicacion) {
        String sql = "INSERT INTO obras(id_material, autor, editorial, num_paginas, edicion, tipo_pasta, genero, idioma, isbn, anio_publicacion) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        ejecutarUpdate(sql, idMaterial, autor, editorial, numPaginas, edicion, tipoPasta, genero, idioma, isbn, anioPublicacion);
    }

    public void guardarDetallesRevista(int idMaterial, String editorial, int numPaginas, String idioma, String fechaPublicacion, String issn, String periodicidad, int numeroEdicion) {
        String sql = "INSERT INTO revistas(id_material, editorial, num_paginas, idioma, fecha_publicacion, issn, periodicidad, numero_edicion) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        ejecutarUpdate(sql, idMaterial, editorial, numPaginas, idioma, fechaPublicacion, issn, periodicidad, numeroEdicion);
    }

    public void guardarDetallesTesis(int idMaterial, String autor, String editorial, int numPaginas, String carrera, String tema, String asesor, String universidad, String grado, String idioma, int anioPublicacion) {
        String sql = "INSERT INTO tesis(id_material, autor, editorial, num_paginas, carrera, tema, asesor, universidad, grado, idioma, anio_publicacion) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        ejecutarUpdate(sql, idMaterial, autor, editorial, numPaginas, carrera, tema, asesor, universidad, grado, idioma, anioPublicacion);
    }

    public void guardarDetallesDocumento(int idMaterial, String autor, String editorial, int numPaginas, String tipoDocumento, String idioma, int anioPublicacion) {
        String sql = "INSERT INTO documentos(id_material, autor, editorial, num_paginas, tipo_documento, idioma, anio_publicacion) VALUES (?, ?, ?, ?, ?, ?, ?)";
        ejecutarUpdate(sql, idMaterial, autor, editorial, numPaginas, tipoDocumento, idioma, anioPublicacion);
    }

    public void guardarDetallesCD(int idMaterial, String artista, String disquera, String genero, String idioma, int numeroCanciones, int anioLanzamiento, int duracion) {
        String sql = "INSERT INTO cd_dvd(id_material, artista, disquera, genero, idioma, numero_canciones, anio_lanzamiento, duracion) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        ejecutarUpdate(sql, idMaterial, artista, disquera, genero, idioma, numeroCanciones, anioLanzamiento, duracion);
    }

    private void ejecutarUpdate(String sql, Object... params) {
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error en detalle: " + e.getMessage());
        }
    }

    // --- MÉTODOS CONSULTA Y BÚSQUEDA ---

    public ResultSet buscarMateriales(String filtro) {
        String sql = "SELECT * FROM materiales WHERE codigo LIKE ? OR titulo LIKE ?";
        try {
            Connection conn = ConexionDB.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);
            String parametro = "%" + filtro + "%";
            stmt.setString(1, parametro);
            stmt.setString(2, parametro);
            return stmt.executeQuery();
        } catch (SQLException e) {
            System.out.println("Error al buscar materiales: " + e.getMessage());
            return null;
        }
    }

    public ResultSet listarTodos() {
        String sql = "SELECT * FROM materiales";
        try {
            Connection conn = ConexionDB.conectar();
            Statement stmt = conn.createStatement();
            return stmt.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println("Error al listar materiales: " + e.getMessage());
            return null;
        }
    }
    public String[] obtenerAutores() {
        String sql = "SELECT DISTINCT autor FROM libros WHERE autor IS NOT NULL ORDER BY autor";
        return obtenerLista(sql);
    }

    public String[] obtenerEditoriales() {
        String sql = "SELECT DISTINCT editorial FROM libros WHERE editorial IS NOT NULL ORDER BY editorial";
        return obtenerLista(sql);
    }

    public String[] obtenerCategorias() {
        String sql = "SELECT DISTINCT categoria FROM materiales WHERE categoria IS NOT NULL ORDER BY categoria";
        return obtenerLista(sql);
    }

    private String[] obtenerLista(String sql) {
        java.util.List<String> lista = new java.util.ArrayList<>();
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(rs.getString(1));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener lista: " + e.getMessage());
        }
        return lista.toArray(new String[0]);
    }
}