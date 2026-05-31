package biblioteca.servlet;
import biblioteca.dao.PrestamoDAO;
import biblioteca.dao.MaterialDAO;
import biblioteca.modelo.Prestamo;
import biblioteca.modelo.Material;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;

@WebServlet("/DevolucionServlet")
public class DevolucionServlet extends HttpServlet {

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
            List<Prestamo> prestamos = dao.listar();
            request.setAttribute("prestamos", prestamos);
            request.getRequestDispatcher("/Devolucion.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            PrestamoDAO prestamoDAO = new PrestamoDAO();
            MaterialDAO materialDAO = new MaterialDAO();
            int idPrestamo = Integer.parseInt(request.getParameter("idPrestamo"));
            int idMaterial = Integer.parseInt(request.getParameter("idMaterial"));
            prestamoDAO.devolver(idPrestamo);
            Material material = materialDAO.buscarPorId(idMaterial);
            material.setCantidad_disponible(material.getCantidad_disponible() + 1);
            materialDAO.actualizar(material);
            response.sendRedirect("DevolucionServlet");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}