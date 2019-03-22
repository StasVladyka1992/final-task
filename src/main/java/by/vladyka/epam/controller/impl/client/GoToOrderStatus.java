package by.vladyka.epam.controller.impl.client;

import by.vladyka.epam.controller.Command;
import by.vladyka.epam.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.vladyka.epam.controller.util.JSPNavigation.ORDER_STATUS;
import static by.vladyka.epam.controller.util.ParameterName.*;

/**
 * Created by Vladyka Stas
 * on 21.03.2019 at 15:34
 **/
public class GoToOrderStatus implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException, IOException, ServletException {
        rememberLastRequest(req);
        req.getSession().removeAttribute(PARAM_NAME_ORDER_DTO);
        req.getSession().removeAttribute(PARAM_NAME_ORDER_DTO);
        req.getSession().removeAttribute(PARAM_NAME_REMEDY_NAME);
        req.getSession().removeAttribute(PARAM_NAME_STORAGE);
        req.getSession().removeAttribute(PARAM_NAME_STORAGE_LIST);
        req.getRequestDispatcher(ORDER_STATUS).forward(req, resp);
    }
}
