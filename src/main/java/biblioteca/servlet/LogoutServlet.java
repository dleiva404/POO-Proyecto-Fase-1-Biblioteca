package biblioteca.servlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet para Logout
 * URL: /BibliotecaWeb/logout
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = LogManager.getLogger(LogoutServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        
        if (session != null) {
            String email = "Desconocido";
            if (session.getAttribute("usuario") != null) {
                email = ((biblioteca.modelo.UsuarioBean) session.getAttribute("usuario")).getEmail();
            }
            
            session.invalidate();
            logger.info("👋 LOGOUT - Usuario: {}", email);
        }
        
        response.sendRedirect("login");
    }
}
