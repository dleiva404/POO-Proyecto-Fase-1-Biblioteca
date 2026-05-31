/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biblioteca.dao;

import biblioteca.db.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    // INSERTAR USUARIO
    public void insertarUsuario(String nombre, String apellido, String carnet, String dui, String telefono, String correo, String tipo, String password) {
        String sql = "INSERT INTO usuarios(nombre, apellido, carnet, dui, telefono, correo, tipo_usuario, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nombre);
            stmt.setString(2, apellido);
            stmt.setString(3, carnet);
            stmt.setString(4, dui);
            stmt.setString(5, telefono);
            stmt.setString(6, correo);
            stmt.setString(7, tipo);
            stmt.setString(8, password);

            stmt.executeUpdate();
            System.out.println("Usuario guardado correctamente");

        } catch (SQLException e) {
            System.out.println("Error al insertar: " + e.getMessage());
        }
    }

    // VERIFICAR CARNET Y CORREO
    public boolean verificarCarnetCorreo(String carnet, String correo) {
        String sql = "SELECT COUNT(*) FROM usuarios WHERE carnet = ? AND correo = ?";
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, carnet);
            stmt.setString(2, correo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar carnet/correo: " + e.getMessage());
        }
        return false;
    }

    // ACTUALIZAR CONTRASEÑA
    public void actualizarPassword(String carnet, String nuevaPassword) {
        String sql = "UPDATE usuarios SET password = ? WHERE carnet = ?";
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nuevaPassword);
            stmt.setString(2, carnet);
            stmt.executeUpdate();
            System.out.println("Contraseña actualizada correctamente");
        } catch (SQLException e) {
            System.out.println("Error al actualizar contraseña: " + e.getMessage());
        }
    }

    // VERIFICAR SI TIENE PRESTAMOS ACTIVOS
    public boolean tienePrestamosActivos(int idUsuario) {
        String sql = "SELECT COUNT(*) FROM prestamos WHERE id_usuario = ? AND estado = 'PRESTADO'";
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar préstamos: " + e.getMessage());
        }
        return false;
    }

    // ELIMINAR USUARIO
    public void eliminarUsuario(int idUsuario) {
        String sqlPrestamos = "DELETE FROM prestamos WHERE id_usuario = ?";
        String sqlUsuario   = "DELETE FROM usuarios WHERE id_usuario = ?";

        try (Connection conn = ConexionDB.conectar()) {
            // Primero eliminar historial de préstamos
            try (PreparedStatement stmt = conn.prepareStatement(sqlPrestamos)) {
                stmt.setInt(1, idUsuario);
                stmt.executeUpdate();
            }
            // Luego eliminar usuario
            try (PreparedStatement stmt = conn.prepareStatement(sqlUsuario)) {
                stmt.setInt(1, idUsuario);
                stmt.executeUpdate();
            }
            System.out.println("Usuario eliminado correctamente");

        } catch (SQLException e) {
            System.out.println("Error al eliminar: " + e.getMessage());
        }
    }

    // ACTUALIZAR ROL
    public void actualizarRol(int idUsuario, String nuevoRol) {
        String sql = "UPDATE usuarios SET tipo_usuario = ? WHERE id_usuario = ?";

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nuevoRol);
            stmt.setInt(2, idUsuario);
            stmt.executeUpdate();
            System.out.println("Rol actualizado correctamente");

        } catch (SQLException e) {
            System.out.println("Error al actualizar rol: " + e.getMessage());
        }
    }

    // LISTAR USUARIOS
    public void listarUsuarios() {
        String sql = "SELECT * FROM usuarios";

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id_usuario"));
                System.out.println("Nombre: " + rs.getString("nombre"));
                System.out.println("Apellido: " + rs.getString("apellido"));
                System.out.println("Carnet: " + rs.getString("carnet"));
                System.out.println("Correo: " + rs.getString("correo"));
                System.out.println("Tipo: " + rs.getString("tipo_usuario"));
                System.out.println("----------------------");
            }

        } catch (SQLException e) {
            System.out.println("Error al listar: " + e.getMessage());
        }
    }

    // LISTAR USUARIOS PARA TABLA
    public ResultSet listarUsuariosResultSet() {
        String sql = "SELECT * FROM usuarios";
        try {
            Connection conn = ConexionDB.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);
            return stmt.executeQuery();
        } catch (SQLException e) {
            System.out.println("Error al listar usuarios: " + e.getMessage());
            return null;
        }
    }

    // OBTENER ID POR CARNET
    public int obtenerIdPorCarnet(String carnet) {
        String sql = "SELECT id_usuario FROM usuarios WHERE carnet = ?";
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, carnet);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id_usuario");
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener id: " + e.getMessage());
        }
        return -1;
    }

    // VALIDAR LOGIN
    public ResultSet validarLogin(String carnet, String password) {
        String sql = "SELECT * FROM usuarios WHERE carnet = ? AND password = ?";
        try {
            Connection conn = ConexionDB.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, carnet);
            stmt.setString(2, password);
            return stmt.executeQuery();
        } catch (SQLException e) {
            System.out.println("Error al validar login: " + e.getMessage());
            return null;
        }
    }
}