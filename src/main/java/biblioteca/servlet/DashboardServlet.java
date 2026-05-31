package biblioteca.servlet;

import biblioteca.modelo.UsuarioBean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet para el Dashboard/Menú principal
 * URL: /BibliotecaWeb/dashboard
 */
@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = LogManager.getLogger(DashboardServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        UsuarioBean usuario = null;

        // Verificar si hay sesión activa
        if (session != null) {
            usuario = (UsuarioBean) session.getAttribute("usuario");
        }

        if (usuario == null) {
            // Sin sesión, redirigir a login
            logger.warn("⚠️ Acceso a dashboard sin sesión");
            response.sendRedirect("login");
            return;
        }

        // Usuario autenticado, mostrar dashboard
        logger.info("📊 Acceso al dashboard - Usuario: {}", usuario.getEmail());
        request.getRequestDispatcher("/WEB-INF/jsp/dashboard.jsp").forward(request, response);
    }
}
