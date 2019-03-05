package by.vladyka.epam.controller.impl;

import by.vladyka.epam.controller.Command;
import by.vladyka.epam.controller.util.URLRestorer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.vladyka.epam.controller.util.JSPNavigation.GO_TO_AUTHORIZATION;

/**
 * Created by Vladyka Stas
 * on 23.02.2019 at 22:29
 **/
public class UserSignOut implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = URLRestorer.restoreURL(req);
        HttpSession session = req.getSession(true);
        session.setAttribute("previous_url", url);
        session.invalidate();
        resp.sendRedirect(GO_TO_AUTHORIZATION);
    }
}
