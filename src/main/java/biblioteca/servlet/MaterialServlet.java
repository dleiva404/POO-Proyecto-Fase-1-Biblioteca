package biblioteca.servlet;

import biblioteca.modelo.MaterialBean;
import biblioteca.modelo.UsuarioBean;
import biblioteca.util.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet para gestión de Materiales
 * URL: /BibliotecaWeb/material
 */
@WebServlet("/material")
public class MaterialServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = LogManager.getLogger(MaterialServlet.java);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Verificar sesión
        HttpSession session = request.getSession(false);
        UsuarioBean usuario = null;
        if (session != null) {
            usuario = (UsuarioBean) session.getAttribute("usuario");
        }

        if (usuario == null) {
            logger.warn("⚠️ Acceso a material sin sesión");
            response.sendRedirect("login");
            return;
        }

        String accion = request.getParameter("accion");
        
        try {
            if (accion == null || accion.equals("listar")) {
                listarMateriales(request, response);
            } else if (accion.equals("buscar")) {
                buscarMaterial(request, response);
            } else if (accion.equals("ver")) {
                verDetalleMaterial(request, response);
            } else {
                listarMateriales(request, response);
            }
        } catch (Exception e) {
            logger.error("Error en MaterialServlet: {}", e.getMessage(), e);
            request.setAttribute("error", "Error al procesar materiales");
            request.getRequestDispatcher("/WEB-INF/jsp/material/listar.jsp").forward(request, response);
        }
    }

    /**
     * Lista todos los materiales disponibles
     */
    private void listarMateriales(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, ServletException, IOException {
        
        List<MaterialBean> materiales = new ArrayList<>();
        String sql = "SELECT id_material, titulo, tipo, cantidad_disponible, ubicacion FROM materiales WHERE cantidad_disponible > 0";

        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                MaterialBean material = new MaterialBean();
                material.setId(rs.getInt("id_material"));
                material.setTitulo(rs.getString("titulo"));
                material.setTipo(rs.getString("tipo"));
                material.setCantidadDisponible(rs.getInt("cantidad_disponible"));
                material.setUbicacion(rs.getString("ubicacion"));
                materiales.add(material);
            }
        }

        logger.info("📚 Listando {} materiales", materiales.size());
        request.setAttribute("materiales", materiales);
        request.getRequestDispatcher("/WEB-INF/jsp/material/listar.jsp").forward(request, response);
    }

    /**
     * Busca materiales por criterio
     */
    private void buscarMaterial(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, ServletException, IOException {
        
        String criterio = request.getParameter("criterio");
        String busqueda = request.getParameter("busqueda");
        List<MaterialBean> materiales = new ArrayList<>();

        String sql = "SELECT id_material, titulo, tipo, cantidad_disponible, ubicacion FROM materiales WHERE cantidad_disponible > 0 AND " + criterio + " LIKE ?";

        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, "%" + busqueda + "%");
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    MaterialBean material = new MaterialBean();
                    material.setId(rs.getInt("id_material"));
                    material.setTitulo(rs.getString("titulo"));
                    material.setTipo(rs.getString("tipo"));
                    material.setCantidadDisponible(rs.getInt("cantidad_disponible"));
                    material.setUbicacion(rs.getString("ubicacion"));
                    materiales.add(material);
                }
            }
        }

        logger.info("🔍 Búsqueda de materiales: {} (Criterio: {})", busqueda, criterio);
        request.setAttribute("materiales", materiales);
        request.setAttribute("busqueda", busqueda);
        request.getRequestDispatcher("/WEB-INF/jsp/material/buscar.jsp").forward(request, response);
    }

    /**
     * Ver detalle de un material
     */
    private void verDetalleMaterial(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, ServletException, IOException {
        
        int idMaterial = Integer.parseInt(request.getParameter("id"));
        String sql = "SELECT * FROM materiales WHERE id_material = ?";

        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idMaterial);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    MaterialBean material = new MaterialBean();
                    material.setId(rs.getInt("id_material"));
                    material.setTitulo(rs.getString("titulo"));
                    material.setTipo(rs.getString("tipo"));
                    material.setCantidadDisponible(rs.getInt("cantidad_disponible"));
                    material.setUbicacion(rs.getString("ubicacion"));
                    material.setAutor(rs.getString("autor"));
                    material.setEditorial(rs.getString("editorial"));
                    
                    request.setAttribute("material", material);
                    logger.info("👁️ Viendo detalle de material: {} (ID: {})", material.getTitulo(), idMaterial);
                }
            }
        }

        request.getRequestDispatcher("/WEB-INF/jsp/material/detalle.jsp").forward(request, response);
    }
}
