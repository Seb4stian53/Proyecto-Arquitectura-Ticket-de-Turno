package proyectoturnos.controllers.Admin;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import proyectoturnos.dao.AdminDAO;
import proyectoturnos.model.Admin;

@WebServlet("/login")
public class AdminLoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String usuario = request.getParameter("usuario");
        String contrasena = request.getParameter("contrasena");

        if (usuario == null || contrasena == null || usuario.isEmpty() || contrasena.isEmpty()) {
            request.setAttribute("error", "Por favor ingrese usuario y contraseña.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        AdminDAO adminDAO = new AdminDAO();
        
        Admin admin = adminDAO.validarCredenciales(usuario, contrasena);
        
        if (admin != null) {
            HttpSession session = request.getSession();
            session.setAttribute("adminUsuario", admin.getUsuario()); // Guardamos el nombre de usuario
            response.sendRedirect(request.getContextPath() + "/panel/dashboard");
        } else {
            request.setAttribute("error", "Usuario o contraseña incorrectos");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
