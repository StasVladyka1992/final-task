package by.vladyka.epam.controller.impl;

import by.vladyka.epam.controller.Command;
import by.vladyka.epam.controller.util.URLRestorer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.vladyka.epam.controller.util.JSPNavigation.REMEDY_ADMINISTRATION;

/**
 * Created by Vladyka Stas
 * on 03.03.2019 at 23:17
 **/
public class RemedyAdministration implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = URLRestorer.restoreURL(req);
        HttpSession session = req.getSession(true);
        session.setAttribute("previous_url", url);
        req.getRequestDispatcher(REMEDY_ADMINISTRATION).forward(req,resp);
    }
}
