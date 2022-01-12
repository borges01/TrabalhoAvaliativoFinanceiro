/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package filter;

import java.io.IOException;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author borges
 */
@WebFilter(dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD}, urlPatterns = "/faces/restricted/*")
public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException { }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        try {

            //Pega a sessão do usuário, caso exista
            HttpSession session = httpRequest.getSession(true);
            boolean logged = session.getAttribute("user_email") != null;
            int userId = (int) session.getAttribute("user_id");

            System.out.println(userId);

            if (logged) {
                //Siga em frente
                chain.doFilter(request, response);
            } else {
//                httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                httpResponse.sendRedirect(httpRequest.getContextPath() + "/");
            }

        } finally {

        }

    }

    @Override
    public void destroy() {}
}

