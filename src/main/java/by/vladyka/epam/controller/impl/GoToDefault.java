package by.vladyka.epam.controller.impl;

import by.vladyka.epam.controller.Command;
import by.vladyka.epam.controller.util.URLRestorer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.vladyka.epam.controller.util.JSPNavigation.DEFAULT;

/**
 * Created by Vladyka Stas
 * on 15.02.2019 at 22:17
 **/
public class GoToDefault implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession(true);
        String url = URLRestorer.restoreURL(req);
        session.setAttribute("previous_url", url);
        String adminMail = (String) req.getServletContext().getAttribute("adminMail");
        session.setAttribute("adminMail", adminMail);
        req.getRequestDispatcher(DEFAULT).forward(req, resp);
    }
}
