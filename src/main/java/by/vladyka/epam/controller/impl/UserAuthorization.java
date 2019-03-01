package by.vladyka.epam.controller.impl;

import by.vladyka.epam.controller.Command;
import by.vladyka.epam.controller.util.URLRestorer;
import by.vladyka.epam.entity.User;
import by.vladyka.epam.service.ServiceCreator;
import by.vladyka.epam.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.vladyka.epam.controller.util.JSPNavigation.*;
import static by.vladyka.epam.controller.util.UserNavigationQualifier.scenarios;

/**
 * Created by Vladyka Stas
 * on 17.02.2019 at 18:26
 **/
public class UserAuthorization implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = URLRestorer.restoreURL(req);
        HttpSession session = req.getSession(true);
        session.setAttribute("previous_url", url);
        User user;
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        try {
            user = ServiceCreator.getInstance().getUserService().authorization(email, password);
            if (user == null) {
                req.setAttribute("commandStatus", "user not found");
                req.getRequestDispatcher(GO_TO_AUTHORIZATION).forward(req,resp);
            } else {
                Character role = user.getRole();
                String command =  scenarios.get(role);
                session.setAttribute("user", user);
                req.getRequestDispatcher(command).forward(req,resp);
            }
        } catch (ServiceException ex) {
//            TODO   log.error(eq)
            throw new ServletException(ex);
        }
    }
}
