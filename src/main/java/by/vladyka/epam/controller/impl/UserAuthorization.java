package by.vladyka.epam.controller.impl;

import by.vladyka.epam.controller.Command;
import by.vladyka.epam.controller.util.URLRestorer;

import by.vladyka.epam.entity.User;
import by.vladyka.epam.service.ServiceProvider;
import by.vladyka.epam.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


import static by.vladyka.epam.controller.util.JSPNavigation.*;
import static by.vladyka.epam.controller.util.ParameterName.*;
import static by.vladyka.epam.controller.util.UserNavigationManager.*;

/**
 * Created by Vladyka Stas
 * on 17.02.2019 at 18:26
 **/
public class UserAuthorization implements Command {
    private static final Logger logger = LogManager.getLogger(UserAuthorization.class);
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        rememberLastPage(req);
        User user;
        String email = req.getParameter(EMAIL);
        String password = req.getParameter(PASSWORD);
        try {
            user = ServiceProvider.getInstance().getUserService().authorization(email, password);
            if (user == null) {
                req.setAttribute(COMMAND_STATUS, USER_NOT_FOUND);
                req.getRequestDispatcher(GO_TO_AUTHORIZATION).forward(req,resp);
            } else {
                Character role = user.getRole();
                String navigationCommand = authorization_scenarios.get(role);
                session.setAttribute(USER, user);
                req.getRequestDispatcher(navigationCommand).forward(req,resp);
            }
        } catch (ServiceException ex) {
            logger.error(ex);
            throw new ServletException();
        }
    }
}
