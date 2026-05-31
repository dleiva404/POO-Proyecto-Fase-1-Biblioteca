package biblioteca.dao;
import biblioteca.util.Conexion;
import biblioteca.modelo.Material;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class MaterialDAO {

    public List<Material> listar() throws SQLException {
        List<Material> lista = new ArrayList<>();
        String sql = "SELECT * FROM materiales";
        try (Connection con = Conexion.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        }
        return lista;
    }

    public List<Material> buscar(String texto) throws SQLException {
        List<Material> lista = new ArrayList<>();
        String sql = "SELECT * FROM materiales WHERE titulo LIKE ? OR categoria LIKE ? OR codigo LIKE ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            String patron = "%" + texto + "%";
            ps.setString(1, patron);
            ps.setString(2, patron);
            ps.setString(3, patron);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        }
        return lista;
    }

    public Material buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM materiales WHERE id_material = ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapear(rs);
        }
        return null;
    }

    public void insertar(Material m) throws SQLException {
        String sql = "INSERT INTO materiales (codigo, titulo, categoria, ubicacion, cantidad_total, cantidad_disponible, tipo_material) VALUES (?,?,?,?,?,?,?)";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, m.getCodigo());
            ps.setString(2, m.getTitulo());
            ps.setString(3, m.getCategoria());
            ps.setString(4, m.getUbicacion());
            ps.setInt(5, m.getCantidad_total());
            ps.setInt(6, m.getCantidad_disponible());
            ps.setString(7, m.getTipo_material());
            ps.executeUpdate();
        }
    }

    public void actualizar(Material m) throws SQLException {
        String sql = "UPDATE materiales SET codigo=?, titulo=?, categoria=?, ubicacion=?, cantidad_total=?, cantidad_disponible=?, tipo_material=? WHERE id_material=?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, m.getCodigo());
            ps.setString(2, m.getTitulo());
            ps.setString(3, m.getCategoria());
            ps.setString(4, m.getUbicacion());
            ps.setInt(5, m.getCantidad_total());
            ps.setInt(6, m.getCantidad_disponible());
            ps.setString(7, m.getTipo_material());
            ps.setInt(8, m.getId_material());
            ps.executeUpdate();
        }
    }

    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM materiales WHERE id_material = ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    private Material mapear(ResultSet rs) throws SQLException {
        Material m = new Material();
        m.setId_material(rs.getInt("id_material"));
        m.setCodigo(rs.getString("codigo"));
        m.setTitulo(rs.getString("titulo"));
        m.setCategoria(rs.getString("categoria"));
        m.setUbicacion(rs.getString("ubicacion"));
        m.setCantidad_total(rs.getInt("cantidad_total"));
        m.setCantidad_disponible(rs.getInt("cantidad_disponible"));
        m.setTipo_material(rs.getString("tipo_material"));
        return m;
    }
}