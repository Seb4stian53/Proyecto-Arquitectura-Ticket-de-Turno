package proyectoturnos.controllers;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false); // No crear si no existe
        if (session != null) {
            session.invalidate(); // Cierra la sesión
        }

        // Redirigir al login
        response.sendRedirect(request.getContextPath() + "/login.jsp");
    }
}
