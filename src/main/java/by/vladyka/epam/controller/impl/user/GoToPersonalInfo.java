package by.vladyka.epam.controller.impl.user;

import by.vladyka.epam.controller.Command;
import by.vladyka.epam.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.vladyka.epam.controller.util.JSPNavigation.PERSONAL_INFO;

/**
 * Created by Vladyka Stas
 * on 19.03.2019 at 12:15
 **/
public class GoToPersonalInfo implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException, IOException, ServletException {
        rememberLastPage(req);
        req.getRequestDispatcher(PERSONAL_INFO).forward(req, resp);
    }
}
