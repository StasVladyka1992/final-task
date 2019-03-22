package by.vladyka.epam.controller.impl.client;

import by.vladyka.epam.controller.Command;
import by.vladyka.epam.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.vladyka.epam.controller.util.JSPNavigation.REMEDY_CLIENT;

/**
 * Created by Vladyka Stas
 * on 15.03.2019 at 13:42
 **/
public class GoToClientSearchingPage implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException, IOException, ServletException {
        rememberLastRequest(req);
        req.getRequestDispatcher(REMEDY_CLIENT).forward(req, resp);
    }
}
