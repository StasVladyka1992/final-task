package by.vladyka.epam.controller.impl.doctor;

import by.vladyka.epam.controller.Command;
import by.vladyka.epam.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.vladyka.epam.controller.util.JSPNavigation.PRESCRIPTION_LIST;

/**
 * Created by Vladyka Stas
 * on 18.03.2019 at 19:12
 **/
public class GoToPrescritptionList implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException, IOException, ServletException {
        rememberLastPage(req);
        req.getRequestDispatcher(PRESCRIPTION_LIST).forward(req, resp);
    }
}
