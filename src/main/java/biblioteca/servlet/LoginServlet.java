package biblioteca.servlet;

import biblioteca.dao.UsuarioDAO;
import biblioteca.modelo.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String carnet   = request.getParameter("carnet");
        String password = request.getParameter("password");

        UsuarioDAO dao = new UsuarioDAO();
        Usuario usuario = dao.login(carnet, password);

        if (usuario != null) {

            HttpSession sesion = request.getSession();
            sesion.setAttribute("usuario", usuario);
            response.sendRedirect("Menu.jsp");
        } else {

            request.setAttribute("error", "Carnet o contraseña incorrectos.");
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        }
    }
}