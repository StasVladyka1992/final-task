package by.vladyka.epam.controller.impl.user;

import by.vladyka.epam.controller.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.vladyka.epam.controller.util.JSPNavigation.REGISTRATION;

/**
 * Created by Vladyka Stas
 * on 16.02.2019 at 0:04
 **/
public class GoToRegistration implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        rememberLastPage(req);
        req.getRequestDispatcher(REGISTRATION).forward(req, resp);
    }
}
