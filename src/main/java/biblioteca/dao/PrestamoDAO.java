package biblioteca.dao;
import biblioteca.util.Conexion;
import biblioteca.modelo.Prestamo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrestamoDAO {

    public List<Prestamo> listar() throws SQLException {
        List<Prestamo> lista = new ArrayList<>();
        String sql = "SELECT p.*, m.titulo, u.nombre, u.apellido FROM prestamos p JOIN materiales m ON p.id_material = m.id_material JOIN usuarios u ON p.id_usuario = u.id_usuario";
        try (Connection con = Conexion.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        }
        return lista;
    }

    public List<Prestamo> listarPorUsuario(int id_usuario) throws SQLException {
        List<Prestamo> lista = new ArrayList<>();
        String sql = "SELECT p.*, m.titulo, u.nombre, u.apellido FROM prestamos p JOIN materiales m ON p.id_material = m.id_material JOIN usuarios u ON p.id_usuario = u.id_usuario WHERE p.id_usuario = ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id_usuario);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        }
        return lista;
    }

    public boolean tieneMora(int id_usuario) throws SQLException {
        String sql = "SELECT COUNT(*) FROM prestamos WHERE id_usuario = ? AND mora > 0 AND estado = 'PRESTADO'";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id_usuario);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt(1) > 0;
        }
        return false;
    }

    public int contarPrestamosActivos(int id_usuario) throws SQLException {
        String sql = "SELECT COUNT(*) FROM prestamos WHERE id_usuario = ? AND estado = 'PRESTADO'";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id_usuario);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt(1);
        }
        return 0;
    }

    public void insertar(Prestamo p) throws SQLException {
        String sql = "INSERT INTO prestamos (id_usuario, id_material, fecha_prestamo, fecha_devolucion, estado, mora) VALUES (?,?,?,?,?,?)";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, p.getId_usuario());
            ps.setInt(2, p.getId_material());
            ps.setString(3, p.getFecha_prestamo());
            ps.setString(4, p.getFecha_devolucion());
            ps.setString(5, p.getEstado());
            ps.setDouble(6, p.getMora());
            ps.executeUpdate();
        }
    }

    public void devolver(int id_prestamo) throws SQLException {
        String sql = "UPDATE prestamos SET estado='DEVUELTO', fecha_devolucion=CURDATE() WHERE id_prestamo=?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id_prestamo);
            ps.executeUpdate();
        }
    }

    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM prestamos WHERE id_prestamo = ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    private Prestamo mapear(ResultSet rs) throws SQLException {
        Prestamo p = new Prestamo();
        p.setId_prestamo(rs.getInt("id_prestamo"));
        p.setId_usuario(rs.getInt("id_usuario"));
        p.setId_material(rs.getInt("id_material"));
        p.setFecha_prestamo(rs.getString("fecha_prestamo"));
        p.setFecha_devolucion(rs.getString("fecha_devolucion"));
        p.setEstado(rs.getString("estado"));
        p.setMora(rs.getDouble("mora"));
        try { p.setTitulo(rs.getString("titulo")); } catch (SQLException e) {}
        try { p.setNombre(rs.getString("nombre")); } catch (SQLException e) {}
        try { p.setApellido(rs.getString("apellido")); } catch (SQLException e) {}
        return p;
    }
}