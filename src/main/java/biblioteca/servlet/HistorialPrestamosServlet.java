package biblioteca.servlet;
import biblioteca.dao.PrestamoDAO;
import biblioteca.modelo.Prestamo;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;

@WebServlet("/HistorialPrestamosServlet")
public class HistorialPrestamosServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuario") == null) {
            response.sendRedirect("Login.jsp");
            return;
        }
        try {
            PrestamoDAO dao = new PrestamoDAO();
            List<Prestamo> historial = dao.listar();
            request.setAttribute("historial", historial);
            request.getRequestDispatcher("/HistorialPrestamos.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}