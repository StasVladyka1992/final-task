package by.vladyka.epam.controller.impl.user;

import by.vladyka.epam.controller.Command;
import by.vladyka.epam.controller.util.URLRestorer;
import by.vladyka.epam.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.vladyka.epam.controller.util.JSPNavigation.DEFAULT;
import static by.vladyka.epam.controller.util.ParameterName.ADMIN_MAIL;
import static by.vladyka.epam.controller.util.ParameterName.PREVIOUS_URL;

/**
 * Created by Vladyka Stas
 * on 15.02.2019 at 22:17
 **/
public class GoToDefault implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String adminMail = (String) req.getServletContext().getAttribute(ADMIN_MAIL);
        HttpSession session = req.getSession(true);
        rememberLastPage(req);
        session.setAttribute(ADMIN_MAIL, adminMail);
        req.getRequestDispatcher(DEFAULT).forward(req, resp);
    }
}