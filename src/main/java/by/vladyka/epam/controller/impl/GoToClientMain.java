package by.vladyka.epam.controller.impl;

import by.vladyka.epam.controller.Command;
import by.vladyka.epam.controller.util.URLRestorer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.vladyka.epam.controller.util.JSPNavigation.CLIENT_MAIN;

/**
 * Created by Vladyka Stas
 * on 24.02.2019 at 13:10
 **/
public class GoToClientMain implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = URLRestorer.restoreURL(req);
        req.getSession(true).setAttribute("previous_url", url);
        req.getRequestDispatcher(CLIENT_MAIN).forward(req, resp);
    }
}
