package biblioteca.dao;

import biblioteca.db.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class PrestamoDAO {

    private static final double MORA_POR_DIA = 0.25;
    private static final int    MAX_ALUMNO   = 3;
    private static final int    MAX_PROFESOR = 5;

    // INSERTAR PRESTAMO
    public void insertarPrestamo(int id_usuario, int id_material, String fecha_prestamo, String fecha_devolucion, String estado) {
        String sql = "INSERT INTO prestamos(id_usuario, id_material, fecha_prestamo, fecha_devolucion, estado) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id_usuario);
            stmt.setInt(2, id_material);
            stmt.setString(3, fecha_prestamo);
            stmt.setString(4, fecha_devolucion);
            stmt.setString(5, estado);
            stmt.executeUpdate();
            System.out.println("Préstamo guardado correctamente");
        } catch (SQLException e) {
            System.out.println("Error al guardar préstamo: " + e.getMessage());
        }
    }

    // CONTAR PRESTAMOS ACTIVOS DEL USUARIO
    public int contarPrestamosActivos(int idUsuario) {
        String sql = "SELECT COUNT(*) FROM prestamos WHERE id_usuario = ? AND estado = 'PRESTADO'";
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) {
            System.out.println("Error al contar préstamos: " + e.getMessage());
        }
        return 0;
    }

    // VERIFICAR SI YA TIENE ESE MATERIAL PRESTADO
    public boolean tieneMaterialPrestado(int idUsuario, int idMaterial) {
        String sql = "SELECT COUNT(*) FROM prestamos WHERE id_usuario = ? AND id_material = ? AND estado = 'PRESTADO'";
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            stmt.setInt(2, idMaterial);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return rs.getInt(1) > 0;
        } catch (SQLException e) {
            System.out.println("Error al verificar material: " + e.getMessage());
        }
        return false;
    }

    // REGISTRAR PRESTAMO COMPLETO
    public boolean registrarPrestamo(int idUsuario, int idMaterial, String tipoUsuario) {
        String fechaPrestamo   = LocalDate.now().toString();
        int diasPrestamo       = tipoUsuario.equalsIgnoreCase("PROFESOR") ? 14 : 7;
        String fechaDevolucion = LocalDate.now().plusDays(diasPrestamo).toString();

        String sql = "INSERT INTO prestamos(id_usuario, id_material, fecha_prestamo, fecha_devolucion, estado, mora) VALUES (?, ?, ?, ?, 'PRESTADO', 0.00)";
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            stmt.setInt(2, idMaterial);
            stmt.setString(3, fechaPrestamo);
            stmt.setString(4, fechaDevolucion);
            stmt.executeUpdate();
            decrementarStock(idMaterial);
            return true;
        } catch (SQLException e) {
            System.out.println("Error al registrar préstamo: " + e.getMessage());
            return false;
        }
    }

    // CALCULAR MORA
    public double calcularMora(String fechaDevolucion) {
        try {
            LocalDate fechaVence = LocalDate.parse(fechaDevolucion);
            LocalDate hoy        = LocalDate.now();
            if (hoy.isAfter(fechaVence)) {
                long diasAtraso = ChronoUnit.DAYS.between(fechaVence, hoy);
                return diasAtraso * MORA_POR_DIA;
            }
        } catch (Exception e) {
            System.out.println("Error al calcular mora: " + e.getMessage());
        }
        return 0.00;
    }

    // OBTENER LIMITE DE PRESTAMOS SEGUN ROL
    public int getLimitePrestamos(String tipoUsuario) {
        return tipoUsuario.equalsIgnoreCase("PROFESOR") ? MAX_PROFESOR : MAX_ALUMNO;
    }

    // DECREMENTAR STOCK AL PRESTAR
    private void decrementarStock(int idMaterial) {
        String sql = "UPDATE materiales SET cantidad_disponible = cantidad_disponible - 1 WHERE id_material = ? AND cantidad_disponible > 0";
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idMaterial);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al decrementar stock: " + e.getMessage());
        }
    }

    // INCREMENTAR STOCK AL DEVOLVER
    public void incrementarStock(int idMaterial) {
        String sql = "UPDATE materiales SET cantidad_disponible = cantidad_disponible + 1 WHERE id_material = ?";
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idMaterial);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al incrementar stock: " + e.getMessage());
        }
    }

    // LISTAR MATERIALES DISPONIBLES
    public ResultSet listarMaterialesDisponibles() {
        String sql = "SELECT id_material, codigo, titulo, tipo_material, cantidad_disponible FROM materiales WHERE cantidad_disponible > 0";
        try {
            Connection conn      = ConexionDB.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);
            return stmt.executeQuery();
        } catch (SQLException e) {
            System.out.println("Error al listar materiales: " + e.getMessage());
            return null;
        }
    }

    // LISTAR PRESTAMOS ACTIVOS
    public ResultSet listarPrestamosActivos() {
        String sql = "SELECT p.id_prestamo, p.id_material, m.titulo, u.nombre, u.apellido, " +
                "p.fecha_prestamo, p.fecha_devolucion, p.estado, p.mora " +
                "FROM prestamos p " +
                "JOIN materiales m ON p.id_material = m.id_material " +
                "JOIN usuarios u ON p.id_usuario = u.id_usuario " +
                "WHERE p.estado = 'PRESTADO'";
        try {
            Connection conn      = ConexionDB.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);
            return stmt.executeQuery();
        } catch (SQLException e) {
            System.out.println("Error al listar préstamos: " + e.getMessage());
            return null;
        }
    }

    // LISTAR HISTORIAL DE PRESTAMOS
    public ResultSet listarHistorialPrestamos() {
        String sql = "SELECT p.id_prestamo, m.titulo, u.nombre, u.apellido, " +
                "p.fecha_prestamo, p.fecha_devolucion, p.estado, p.mora " +
                "FROM prestamos p " +
                "JOIN materiales m ON p.id_material = m.id_material " +
                "JOIN usuarios u ON p.id_usuario = u.id_usuario " +
                "ORDER BY p.fecha_prestamo DESC";
        try {
            Connection conn      = ConexionDB.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);
            return stmt.executeQuery();
        } catch (SQLException e) {
            System.out.println("Error al listar historial: " + e.getMessage());
            return null;
        }
    }

    // PROCESAR DEVOLUCION — retorna boolean para que el Frame pueda mostrar éxito o error
    public boolean procesarDevolucion(int idPrestamo, int idMaterial, double mora) {
        String sql = "UPDATE prestamos SET estado = 'DEVUELTO', fecha_devolucion = CURDATE(), mora = ? WHERE id_prestamo = ?";
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, mora);
            stmt.setInt(2, idPrestamo);
            stmt.executeUpdate();
            incrementarStock(idMaterial);
            return true;
        } catch (SQLException e) {
            System.out.println("Error al procesar devolución: " + e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) {
        PrestamoDAO dao = new PrestamoDAO();
        dao.insertarPrestamo(1, 1, "2026-05-08", "2026-05-15", "PRESTADO");
    }
}