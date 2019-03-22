package by.vladyka.epam.controller.impl.user;

import by.vladyka.epam.controller.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.vladyka.epam.controller.util.JSPNavigation.AUTHORIZATION;

public class GoToAutorization implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse response) throws IOException, ServletException {
        rememberLastRequest(req);
        req.getRequestDispatcher(AUTHORIZATION).forward(req, response);
    }
}
