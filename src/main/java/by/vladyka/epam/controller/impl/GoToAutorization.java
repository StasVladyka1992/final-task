package by.vladyka.epam.controller.impl;

import by.vladyka.epam.controller.Command;
import by.vladyka.epam.controller.util.URLRestorer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.vladyka.epam.controller.util.JSPNavigation.AUTHORIZATION;

public class GoToAutorization implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse response) throws IOException, ServletException {
        String url = URLRestorer.restoreURL(req);
        req.getSession(true).setAttribute("previous_url", url);
        req.getRequestDispatcher(AUTHORIZATION).forward(req, response);
    }
}
