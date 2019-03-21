package by.vladyka.epam.controller.impl.client;

import by.vladyka.epam.controller.Command;
import by.vladyka.epam.dto.OrderDto;
import by.vladyka.epam.entity.Storage;
import by.vladyka.epam.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

import static by.vladyka.epam.controller.util.JSPNavigation.GO_TO_BASKET;
import static by.vladyka.epam.controller.util.ParameterName.*;
import static by.vladyka.epam.controller.util.ParameterValue.PARAM_VALUE_QUANTITY_CHANGED;
import static by.vladyka.epam.service.validator.util.IncorrectDataMessage.INCORRECT_QUANTITY;

/**
 * Created by Vladyka Stas
 * on 20.03.2019 at 18:50
 **/
public class ChangeQuantity implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException, IOException, ServletException {
        int quantity = Integer.parseInt(req.getParameter(PARAM_NAME_QUANTITY));
        if (quantity<=0){
            resp.sendRedirect(GO_TO_BASKET+INCORRECT_QUANTITY);
        }
        int id = Integer.parseInt(req.getParameter(PARAM_NAME_ID));
        OrderDto orderDto = (OrderDto) req.getSession().getAttribute(PARAM_NAME_ORDER_DTO);
        for (Map.Entry<Storage, Integer> good :
                orderDto.getGoods().entrySet()) {
            if (good.getKey().getId() == id) {
                good.setValue(quantity);
                break;
            }
        }
        resp.sendRedirect(GO_TO_BASKET + PARAM_NAME_OPERATION_RESULT + "=" + PARAM_VALUE_QUANTITY_CHANGED);
    }
}
