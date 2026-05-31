package biblioteca.dao;

import biblioteca.db.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConfiguracionDAO {

    // Obtener valor de configuracion
    public double getMoraDiaria() {
        String sql = "SELECT valor FROM configuracion WHERE clave = 'mora_diaria'";
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return Double.parseDouble(rs.getString("valor"));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el valor de la mora: " + e.getMessage());
        }
        return 0.25;
    }

    // Guardar nuevo valor de mora
    public void setMoraDiaria(double valor) {
        String sql = "UPDATE configuracion SET valor = ? WHERE clave = 'mora_diaria'";
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, String.valueOf(valor));
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al guardar el valor de la mora: " + e.getMessage());
        }
    }
}