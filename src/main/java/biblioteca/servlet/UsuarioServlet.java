package biblioteca.servlet;
import biblioteca.dao.UsuarioDAO;
import biblioteca.modelo.Usuario;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;

@WebServlet({"/usuarios", "/UsuarioServlet", "/UsuariosServlet"})
public class UsuarioServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuario") == null) {
            response.sendRedirect("Login.jsp");
            return;
        }
        String accion = request.getParameter("accion");
        if (accion == null) accion = "listar";
        try {
            UsuarioDAO dao = new UsuarioDAO();
            switch (accion) {
                case "listar":
                    List<Usuario> lista = dao.listar();
                    request.setAttribute("usuarios", lista);
                    request.getRequestDispatcher("/Usuarios.jsp").forward(request, response);
                    break;
                case "editar":
                    int id = Integer.parseInt(request.getParameter("id"));
                    Usuario u = dao.buscarPorId(id);
                    request.setAttribute("usuarioEditar", u);
                    request.getRequestDispatcher("/Usuarios.jsp").forward(request, response);
                    break;
                case "eliminar":
                    int idEliminar = Integer.parseInt(request.getParameter("id"));
                    dao.eliminar(idEliminar);
                    response.sendRedirect("UsuarioServlet");
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
            UsuarioDAO dao = new UsuarioDAO();
            if ("eliminar".equals(accion)) {
                int id = Integer.parseInt(request.getParameter("idUsuario"));
                dao.eliminar(id);
            } else if ("cambiarRol".equals(accion)) {
                int id = Integer.parseInt(request.getParameter("idUsuario"));
                String nuevoRol = request.getParameter("nuevoRol");
                Usuario u = dao.buscarPorId(id);
                u.setTipo_usuario(nuevoRol);
                dao.actualizar(u);
            }
            response.sendRedirect("UsuarioServlet");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}