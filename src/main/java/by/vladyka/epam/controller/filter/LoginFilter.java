package by.vladyka.epam.controller.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.vladyka.epam.controller.util.JSPNavigation.GO_TO_AUTHORIZATION;
import static by.vladyka.epam.controller.util.ParameterName.PARAM_NAME_SESSION_EXPIRED;
import static by.vladyka.epam.controller.util.ParameterName.PARAM_NAME_USER;
import static by.vladyka.epam.controller.util.ParameterValue.PARAM_VALUE_TRUE;

/**
 * Created by Vladyka Stas
 * on 23.02.2019 at 21:24
 **/
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute(PARAM_NAME_USER) == null) {
            response.sendRedirect(GO_TO_AUTHORIZATION + PARAM_NAME_SESSION_EXPIRED + "=" + PARAM_VALUE_TRUE);
        } else {
            filterChain.doFilter(request, servletResponse);
        }
    }

    @Override
    public void destroy() {
    }
}
