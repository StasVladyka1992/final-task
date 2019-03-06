package by.vladyka.epam.controller.impl;

import by.vladyka.epam.controller.Command;
import by.vladyka.epam.controller.util.URLRestorer;
import by.vladyka.epam.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.vladyka.epam.controller.util.JSPNavigation.DEFAULT;
import static by.vladyka.epam.controller.util.ParameterName.PREVIOUS_URL;

/**
 * Created by Vladyka Stas
 * on 15.02.2019 at 22:17
 **/
public class GoToDefault implements Command {
    private static final String ADMIN_MAIL = "adminMail";
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession(true);
        rememberLastPage(req);
        String adminMail = (String) req.getServletContext().getAttribute(ADMIN_MAIL);
        session.setAttribute(ADMIN_MAIL, adminMail);
        req.getRequestDispatcher(DEFAULT).forward(req, resp);
    }
}
