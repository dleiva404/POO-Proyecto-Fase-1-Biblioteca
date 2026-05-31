package biblioteca.servlet;
import biblioteca.dao.MaterialDAO;
import biblioteca.dao.PrestamoDAO;
import biblioteca.modelo.Material;
import biblioteca.modelo.Prestamo;
import biblioteca.modelo.Usuario;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet({"/prestamos", "/PrestamoServlet"})
public class PrestamoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuario") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        String accion = request.getParameter("accion");
        if (accion == null) accion = "listar";
        try {
            PrestamoDAO prestamoDAO = new PrestamoDAO();
            MaterialDAO materialDAO = new MaterialDAO();
            Usuario usuarioSesion = (Usuario) session.getAttribute("usuario");
            switch (accion) {
                case "listar":
                    List<Material> todosMateriales = materialDAO.listar();
                    request.setAttribute("materiales", todosMateriales);
                    int limiteL = "PROFESOR".equals(usuarioSesion.getTipo_usuario()) ? 6 : 3;
                    int activosL = prestamoDAO.contarPrestamosActivos(usuarioSesion.getId_usuario());
                    request.setAttribute("limite", limiteL);
                    request.setAttribute("activos", activosL);
                    request.getRequestDispatcher("/Prestamo.jsp").forward(request, response);
                    break;
                case "buscar":
                    String texto = request.getParameter("texto");
                    List<Material> resultados = materialDAO.buscar(texto);
                    request.setAttribute("materiales", resultados);
                    request.setAttribute("texto", texto);
                    int limite = "PROFESOR".equals(usuarioSesion.getTipo_usuario()) ? 6 : 3;
                    int activos = prestamoDAO.contarPrestamosActivos(usuarioSesion.getId_usuario());
                    request.setAttribute("limite", limite);
                    request.setAttribute("activos", activos);
                    request.getRequestDispatcher("/Prestamo.jsp").forward(request, response);
                    break;
                case "devolver":
                    int idPrestamo = Integer.parseInt(request.getParameter("id"));
                    Prestamo p = new Prestamo();
                    p.setId_prestamo(idPrestamo);
                    prestamoDAO.devolver(idPrestamo);
                    Prestamo prestamo = prestamoDAO.listar().stream()
                            .filter(x -> x.getId_prestamo() == idPrestamo)
                            .findFirst().orElse(null);
                    response.sendRedirect(request.getContextPath() + "/prestamos");
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
        HttpSession session = request.getSession(false);
        Usuario usuarioSesion = (Usuario) session.getAttribute("usuario");
        try {
            PrestamoDAO prestamoDAO = new PrestamoDAO();
            MaterialDAO materialDAO = new MaterialDAO();
            int idMaterial = Integer.parseInt(request.getParameter("id_material"));
            Material material = materialDAO.buscarPorId(idMaterial);
            if (prestamoDAO.tieneMora(usuarioSesion.getId_usuario())) {
                request.setAttribute("error", "No puede realizar préstamos, tiene mora pendiente.");
                request.setAttribute("materiales", materialDAO.listar());
                request.getRequestDispatcher("/Prestamo.jsp").forward(request, response);
                return;
            }
            int limite = "PROFESOR".equals(usuarioSesion.getTipo_usuario()) ? 6 : 3;
            int activos = prestamoDAO.contarPrestamosActivos(usuarioSesion.getId_usuario());
            if (activos >= limite) {
                request.setAttribute("error", "Ha alcanzado el límite de préstamos permitidos (" + limite + ").");
                request.setAttribute("materiales", materialDAO.listar());
                request.getRequestDispatcher("/Prestamo.jsp").forward(request, response);
                return;
            }
            if (material.getCantidad_disponible() <= 0) {
                request.setAttribute("error", "El material no tiene unidades disponibles.");
                request.setAttribute("materiales", materialDAO.listar());
                request.getRequestDispatcher("/Prestamo.jsp").forward(request, response);
                return;
            }
            Prestamo prestamo = new Prestamo();
            prestamo.setId_usuario(usuarioSesion.getId_usuario());
            prestamo.setId_material(idMaterial);
            prestamo.setFecha_prestamo(LocalDate.now().toString());
            prestamo.setFecha_devolucion(LocalDate.now().plusDays(7).toString());
            prestamo.setEstado("PRESTADO");
            prestamo.setMora(0.0);
            prestamoDAO.insertar(prestamo);
            material.setCantidad_disponible(material.getCantidad_disponible() - 1);
            materialDAO.actualizar(material);
            response.sendRedirect(request.getContextPath() + "/prestamos");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}