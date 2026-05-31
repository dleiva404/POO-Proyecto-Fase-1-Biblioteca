package biblioteca.servlet;
import biblioteca.dao.MaterialDAO;
import biblioteca.modelo.Material;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;

@WebServlet("/ConsultarMaterialServlet")
public class ConsultarMaterialServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuario") == null) {
            response.sendRedirect("Login.jsp");
            return;
        }
        try {
            MaterialDAO dao = new MaterialDAO();
            String texto = request.getParameter("texto");
            List<Material> lista;
            if (texto != null && !texto.isEmpty()) {
                lista = dao.buscar(texto);
                request.setAttribute("texto", texto);
            } else {
                lista = dao.listar();
            }
            request.setAttribute("lista", lista);
            request.getRequestDispatcher("/ConsultarMaterial.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}