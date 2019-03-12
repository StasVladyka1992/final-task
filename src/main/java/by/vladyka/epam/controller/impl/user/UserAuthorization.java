package by.vladyka.epam.controller.impl.user;

import by.vladyka.epam.controller.Command;
import by.vladyka.epam.entity.User;
import by.vladyka.epam.service.ServiceProvider;
import by.vladyka.epam.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.vladyka.epam.controller.util.JSPNavigation.GO_TO_AUTHORIZATION;
import static by.vladyka.epam.controller.util.JSPNavigation.GO_TO_AUTHORIZED_MAIN;
import static by.vladyka.epam.controller.util.ParameterName.*;
import static by.vladyka.epam.controller.util.ParameterValue.USER_NOT_FOUND;

/**
 * Created by Vladyka Stas
 * on 17.02.2019 at 18:26
 **/
public class UserAuthorization implements Command {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException, IOException {
        HttpSession session = req.getSession(true);
        rememberLastPage(req);
        User user;
        String email = req.getParameter(EMAIL);
        String password = req.getParameter(PASSWORD);

        user = ServiceProvider.getInstance().getUserService().authorization(email, password);
        if (user == null) {
            resp.sendRedirect(GO_TO_AUTHORIZATION + COMMAND_STATUS + "=" + USER_NOT_FOUND);
        } else {
            session.setAttribute(USER, user);
            resp.sendRedirect(GO_TO_AUTHORIZED_MAIN);
        }
    }
}
