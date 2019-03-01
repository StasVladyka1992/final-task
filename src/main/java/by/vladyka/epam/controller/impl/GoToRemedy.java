package by.vladyka.epam.controller.impl;

import by.vladyka.epam.controller.Command;
import by.vladyka.epam.controller.util.URLRestorer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.vladyka.epam.controller.util.JSPNavigation.REMEDY;

/**
 * Created by Vladyka Stas
 * on 26.02.2019 at 1:44
 **/
public class GoToRemedy implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        String url = URLRestorer.restoreURL(req);
        session.setAttribute("previous_url", url);
        req.getRequestDispatcher(REMEDY).forward(req, resp);
    }
}
