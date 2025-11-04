package filtros;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebFilter("/panel/*")
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // No se necesita configuración especial
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);

        // Verifica si el admin está autenticado
        boolean loggedIn = (session != null && session.getAttribute("adminUsuario") != null);
        String loginURI = request.getContextPath() + "/login.jsp";

        boolean loginRequest = request.getRequestURI().equals(loginURI);
        boolean isCssRequest = request.getRequestURI().endsWith(".css");

         if (loggedIn || loginRequest || isCssRequest) { 
            // Permitir acceso
            chain.doFilter(request, response);
        } else {
            // Redirigir al login
            response.sendRedirect(loginURI);
        }
    }

    @Override
    public void destroy() {
        // No se necesita limpieza
    }
}
