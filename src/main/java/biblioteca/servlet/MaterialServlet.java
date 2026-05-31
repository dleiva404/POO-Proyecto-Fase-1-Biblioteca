package biblioteca.servlet;

import biblioteca.dao.MaterialDAO;
import biblioteca.modelo.Material;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;

@WebServlet("/materiales")
public class MaterialServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("Usuario") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String accion = request.getParameter("accion");
        if (accion == null) accion = "listar";

        try {
            MaterialDAO dao = new MaterialDAO();

            switch (accion) {
                case "listar":
                    List<Material> lista = dao.listar();
                    request.setAttribute("lista", lista);
                    request.getRequestDispatcher("/materiales.jsp").forward(request, response);
                    break;

                case "editar":
                    int id = Integer.parseInt(request.getParameter("id"));
                    Material m = dao.buscarPorId(id);
                    request.setAttribute("material", m);
                    request.getRequestDispatcher("/materialForm.jsp").forward(request, response);
                    break;

                case "eliminar":
                    int idEliminar = Integer.parseInt(request.getParameter("id"));
                    dao.eliminar(idEliminar);
                    response.sendRedirect(request.getContextPath() + "/materiales");
                    break;

                case "nuevo":
                    request.getRequestDispatcher("/materialForm.jsp").forward(request, response);
                    break;

                case "buscar":
                    String texto = request.getParameter("texto");
                    List<Material> resultados = dao.buscar(texto);
                    request.setAttribute("lista", resultados);
                    request.setAttribute("texto", texto);
                    request.getRequestDispatcher("/materiales.jsp").forward(request, response);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String accion = request.getParameter("accion");

        try {
            MaterialDAO dao = new MaterialDAO();
            Material m = new Material();
            m.setCodigo(request.getParameter("codigo"));
            m.setTitulo(request.getParameter("titulo"));
            m.setCategoria(request.getParameter("categoria"));
            m.setUbicacion(request.getParameter("ubicacion"));
            m.setCantidad_total(Integer.parseInt(request.getParameter("cantidad_total")));
            m.setCantidad_disponible(Integer.parseInt(request.getParameter("cantidad_disponible")));
            m.setTipo_material(request.getParameter("tipo_material"));

            if ("insertar".equals(accion)) {
                dao.insertar(m);
            } else if ("actualizar".equals(accion)) {
                m.setId_material(Integer.parseInt(request.getParameter("id_material")));
                dao.actualizar(m);
            }
            response.sendRedirect(request.getContextPath() + "/materiales");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}