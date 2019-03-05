package by.vladyka.epam.controller.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.vladyka.epam.controller.util.JSPNavigation.GO_TO_AUTHORIZATION;

/**
 * Created by Vladyka Stas
 * on 23.02.2019 at 21:24
 **/
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);

        if (session.getAttribute("user") == null || session==null) {
            request.getRequestDispatcher(GO_TO_AUTHORIZATION).forward(request, response);
        } else {
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
    }
}
