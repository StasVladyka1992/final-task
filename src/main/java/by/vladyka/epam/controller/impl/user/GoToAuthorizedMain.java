package by.vladyka.epam.controller.impl.user;

import by.vladyka.epam.controller.Command;
import by.vladyka.epam.controller.util.URLRestorer;
import by.vladyka.epam.entity.User;
import by.vladyka.epam.entity.UserRole;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.vladyka.epam.controller.util.ParameterName.PREVIOUS_URL;
import static by.vladyka.epam.controller.util.ParameterName.USER;
import static by.vladyka.epam.controller.util.UserNavigationManager.*;
/**
 * Created by Vladyka Stas
 * on 23.02.2019 at 21:37
 **/
public class GoToAuthorizedMain implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        User user = (User) session.getAttribute(USER);
        UserRole role = user.getRole();
        String navigationCommand = authorization_scenarios.get(role);
        rememberLastPage(req);
        req.getRequestDispatcher(navigationCommand).forward(req,resp);
    }
}