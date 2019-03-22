package by.vladyka.epam.controller.impl.client;

import by.vladyka.epam.controller.Command;
import by.vladyka.epam.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.vladyka.epam.controller.util.JSPNavigation.BASKET;

/**
 * Created by Vladyka Stas
 * on 19.03.2019 at 11:27
 **/
public class GoToBasket implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException, IOException, ServletException {
        rememberLastRequest(req);
        req.getRequestDispatcher(BASKET).forward(req, resp);
    }
}
