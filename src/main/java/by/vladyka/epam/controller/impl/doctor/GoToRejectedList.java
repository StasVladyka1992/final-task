package by.vladyka.epam.controller.impl.doctor;

import by.vladyka.epam.controller.Command;
import by.vladyka.epam.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.vladyka.epam.controller.util.JSPNavigation.REJECTION_LIST;

/**
 * Created by Vladyka Stas
 * on 18.03.2019 at 15:14
 **/
public class GoToRejectedList implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException, IOException, ServletException {
        rememberLastRequest(req);
        req.getRequestDispatcher(REJECTION_LIST).forward(req,resp);
    }
}
