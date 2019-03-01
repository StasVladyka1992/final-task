package by.vladyka.epam.controller.impl;

import by.vladyka.epam.controller.Command;
import by.vladyka.epam.controller.util.URLRestorer;
import by.vladyka.epam.controller.util.UserNavigationQualifier;
import by.vladyka.epam.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.vladyka.epam.controller.util.JSPNavigation.*;
import static by.vladyka.epam.controller.util.UserNavigationQualifier.scenarios;

/**
 * Created by Vladyka Stas
 * on 23.02.2019 at 21:37
 **/
public class GoToAuthorizedMain implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        User user = (User) session.getAttribute("user");
        String url = URLRestorer.restoreURL(req);
        session.setAttribute("previous_url", url);

        Character role = user.getRole();
        String command =  scenarios.get(role);
        req.getRequestDispatcher(command).forward(req,resp);
    }
}
