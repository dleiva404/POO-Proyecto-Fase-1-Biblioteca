package biblioteca.servlet;

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

/**
 * Servlet para manejo de Login
 * URL: /BibliotecaWeb/login
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = LogManager.getLogger(LoginServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Mostrar página de login
        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String error = null;

        try {
            // Validar campos vacíos
            if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
                error = "Email y contraseña son requeridos";
                logger.warn("❌ Intento de login con campos vacíos");
            } else {
                // Buscar usuario en BD
                UsuarioBean usuario = autenticarUsuario(email, password);
                
                if (usuario != null) {
                    // Login exitoso
                    HttpSession session = request.getSession();
                    session.setAttribute("usuario", usuario);
                    session.setMaxInactiveInterval(30 * 60); // 30 minutos
                    
                    logger.info("✅ LOGIN EXITOSO - Usuario: {} ({})", usuario.getEmail(), usuario.getTipoUsuario());
                    
                    // Redirigir a dashboard según tipo de usuario
                    response.sendRedirect("dashboard");
                    return;
                } else {
                    error = "Email o contraseña incorrectos";
                    logger.warn("❌ LOGIN FALLIDO - Email: {}", email);
                }
            }
        } catch (Exception e) {
            error = "Error al procesar login: " + e.getMessage();
            logger.error("Error en LoginServlet: {}", e.getMessage(), e);
        }

        // Si hay error, volver a login con mensaje
        request.setAttribute("error", error);
        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
    }

    /**
     * Autentica un usuario contra la base de datos
     */
    private UsuarioBean autenticarUsuario(String email, String password) throws SQLException {
        String sql = "SELECT id_usuario, nombre, apellido, email, tipo_usuario FROM usuarios WHERE email = ? AND password = ?";
        
        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, email);
            stmt.setString(2, password);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    UsuarioBean usuario = new UsuarioBean();
                    usuario.setId(rs.getInt("id_usuario"));
                    usuario.setNombre(rs.getString("nombre"));
                    usuario.setApellido(rs.getString("apellido"));
                    usuario.setEmail(rs.getString("email"));
                    usuario.setTipoUsuario(rs.getString("tipo_usuario"));
                    usuario.setActivo(true);
                    return usuario;
                }
            }
        }
        return null;
    }
}
