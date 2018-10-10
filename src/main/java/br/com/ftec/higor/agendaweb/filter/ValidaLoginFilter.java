package br.com.ftec.higor.agendaweb.filter;

import br.com.ftec.higor.agendaweb.util.JdbcUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Servlet Filter implementation class ValidaLoginFilter
 */
@WebFilter(urlPatterns = { "/*" })
public class ValidaLoginFilter implements Filter {

    /**
     * Default constructor.
     */
    public ValidaLoginFilter() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @see Filter#destroy()
     */
    public void destroy() {
        // TODO Auto-generated method stub
    }

    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        if (request instanceof HttpServletRequest) {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            String path = httpRequest.getServletPath();

            String usuario = (String) httpRequest.getSession().getAttribute("usuario");
            if (usuario != null) {
                // usuario logado
                request.setAttribute("usuario", usuario);
                if ("/".equals(path) || "/login".equals(path)) {
                    httpResponse.sendRedirect("home");
                    return;
                }
            } else {
                // usuario deslogado
                if (!"/cadastro".equals(path) && !"/login".equals(path)) {
                    httpResponse.sendRedirect("login");
                    return;
                }
            }

        }

		try (Connection c = JdbcUtil.getDataSource().getConnection()) {
			request.setAttribute("dbconn", c);
			chain.doFilter(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
        // TODO Auto-generated method stub
    }

}
