package biblioteca.dao;
import biblioteca.util.Conexion;
import biblioteca.modelo.Usuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class UsuarioDAO {

    public Usuario login(String carnet, String password) {
        String sql = "SELECT * FROM usuarios WHERE carnet = ? AND password = ?";
        try (Connection conn = Conexion.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, carnet);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Usuario u = new Usuario();
                u.setId_usuario(rs.getInt("id_usuario"));
                u.setNombre(rs.getString("nombre"));
                u.setApellido(rs.getString("apellido"));
                u.setCarnet(rs.getString("carnet"));
                u.setDui(rs.getString("dui"));
                u.setTelefono(rs.getString("telefono"));
                u.setCorreo(rs.getString("correo"));
                u.setTipo_usuario(rs.getString("tipo_usuario"));
                u.setPassword(rs.getString("password"));
                return u;
            }
        } catch (SQLException e) {
            System.out.println("Error en login: " + e.getMessage());
        }
        return null;
    }

    public List<Usuario> listar() throws SQLException {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";
        try (Connection con = Conexion.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        }
        return lista;
    }

    public void insertar(Usuario u) throws SQLException {
        String sql = "INSERT INTO usuarios (nombre, apellido, carnet, dui, telefono, correo, tipo_usuario, password) VALUES (?,?,?,?,?,?,?,?)";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, u.getNombre());
            ps.setString(2, u.getApellido());
            ps.setString(3, u.getCarnet());
            ps.setString(4, u.getDui());
            ps.setString(5, u.getTelefono());
            ps.setString(6, u.getCorreo());
            ps.setString(7, u.getTipo_usuario());
            ps.setString(8, u.getPassword());
            ps.executeUpdate();
        }
    }

    public void actualizar(Usuario u) throws SQLException {
        String sql = "UPDATE usuarios SET nombre=?, apellido=?, carnet=?, dui=?, telefono=?, correo=?, tipo_usuario=?, password=? WHERE id_usuario=?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, u.getNombre());
            ps.setString(2, u.getApellido());
            ps.setString(3, u.getCarnet());
            ps.setString(4, u.getDui());
            ps.setString(5, u.getTelefono());
            ps.setString(6, u.getCorreo());
            ps.setString(7, u.getTipo_usuario());
            ps.setString(8, u.getPassword());
            ps.setInt(9, u.getId_usuario());
            ps.executeUpdate();
        }
    }

    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM usuarios WHERE id_usuario = ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public Usuario buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM usuarios WHERE id_usuario = ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapear(rs);
        }
        return null;
    }

    private Usuario mapear(ResultSet rs) throws SQLException {
        Usuario u = new Usuario();
        u.setId_usuario(rs.getInt("id_usuario"));
        u.setNombre(rs.getString("nombre"));
        u.setApellido(rs.getString("apellido"));
        u.setCarnet(rs.getString("carnet"));
        u.setDui(rs.getString("dui"));
        u.setTelefono(rs.getString("telefono"));
        u.setCorreo(rs.getString("correo"));
        u.setTipo_usuario(rs.getString("tipo_usuario"));
        u.setPassword(rs.getString("password"));
        return u;
    }
}