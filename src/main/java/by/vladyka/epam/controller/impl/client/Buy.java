package by.vladyka.epam.controller.impl.client;

import by.vladyka.epam.controller.Command;
import by.vladyka.epam.dto.OrderDto;
import by.vladyka.epam.entity.User;
import by.vladyka.epam.service.ClientOrderService;
import by.vladyka.epam.service.RemedyOrderService;
import by.vladyka.epam.service.ServiceProvider;
import by.vladyka.epam.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.vladyka.epam.controller.util.JSPNavigation.GO_TO_ORDER_STATUS;
import static by.vladyka.epam.controller.util.ParameterName.*;
import static by.vladyka.epam.controller.util.ParameterValue.PARAM_VALUE_OPERATION_RESULT_FAIL;
import static by.vladyka.epam.controller.util.ParameterValue.PARAM_VALUE_OPERATION_RESULT_SUCCESS;

/**
 * Created by Vladyka Stas
 * on 21.03.2019 at 0:53
 **/
public class Buy implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException, IOException, ServletException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(PARAM_NAME_USER);
        ServiceProvider provider = ServiceProvider.getInstance();
        ClientOrderService clientOrderService = provider.getClientOrderService();
        OrderDto orderDto = (OrderDto) session.getAttribute(PARAM_NAME_ORDER_DTO);
        int clientOrderId = clientOrderService.buy(user.getId(),orderDto);
        if (clientOrderId == -1) {
            resp.sendRedirect(GO_TO_ORDER_STATUS + PARAM_NAME_OPERATION_RESULT + '=' +
                    PARAM_VALUE_OPERATION_RESULT_FAIL);
        }
        RemedyOrderService remedyOrderService = provider.getRemedyOrderService();
        boolean result = remedyOrderService.create(orderDto, clientOrderId);
        if (result) {
            resp.sendRedirect(GO_TO_ORDER_STATUS + PARAM_NAME_OPERATION_RESULT + "=" +
                    PARAM_VALUE_OPERATION_RESULT_SUCCESS);
        }
    }
}
