package by.vladyka.epam.controller.impl.user;

import by.vladyka.epam.controller.Command;

import by.vladyka.epam.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.vladyka.epam.controller.util.ParameterName.PARAM_NAME_USER;
import static by.vladyka.epam.controller.util.UserNavigationManager.remedy_searching_scenarios;
/**
 * Created by Vladyka Stas
 * on 26.02.2019 at 1:44
 **/
public class GoToSearchingPage implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        User user = (User)session.getAttribute(PARAM_NAME_USER);
        String navigationCommand = remedy_searching_scenarios.get(user.getRole());
        rememberLastRequest(req);
        req.getRequestDispatcher(navigationCommand).forward(req, resp);
    }
}
