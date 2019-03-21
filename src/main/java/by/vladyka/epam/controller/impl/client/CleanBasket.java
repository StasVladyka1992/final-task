package by.vladyka.epam.controller.impl.client;

import by.vladyka.epam.controller.Command;
import by.vladyka.epam.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.vladyka.epam.controller.util.JSPNavigation.GO_TO_BASKET;
import static by.vladyka.epam.controller.util.ParameterName.PARAM_NAME_OPERATION_RESULT;
import static by.vladyka.epam.controller.util.ParameterName.PARAM_NAME_ORDER_DTO;
import static by.vladyka.epam.controller.util.ParameterValue.PARAM_VALUE_BASKET_CLEANED;

/**
 * Created by Vladyka Stas
 * on 21.03.2019 at 0:17
 **/
public class CleanBasket implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException, IOException, ServletException {
        HttpSession session = req.getSession();
        session.removeAttribute(PARAM_NAME_ORDER_DTO);
        resp.sendRedirect(GO_TO_BASKET+PARAM_NAME_OPERATION_RESULT+"="+PARAM_VALUE_BASKET_CLEANED);
    }
}
