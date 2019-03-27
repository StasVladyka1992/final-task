package by.vladyka.epam.controller.impl.client;

import by.vladyka.epam.controller.Command;
import by.vladyka.epam.dto.OrderDto;
import by.vladyka.epam.entity.Storage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static by.vladyka.epam.controller.util.JSPNavigation.GO_TO_CLIENT_SEARCHING_PAGE;
import static by.vladyka.epam.controller.util.ParameterName.*;
import static by.vladyka.epam.controller.util.ParameterValue.PARAM_VALUE_GOOD_ALREADY_IN_BASKET;
import static by.vladyka.epam.controller.util.ParameterValue.PARAM_VALUE_OPERATION_RESULT_SUCCESS;
import static by.vladyka.epam.service.validator.util.IncorrectDataMessage.INCORRECT_QUANTITY;

/**
 * Created by Vladyka Stas
 * on 15.03.2019 at 0:17
 **/
public class AddToBasket implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int quantity = Integer.parseInt(req.getParameter(PARAM_NAME_QUANTITY));
        if (quantity <= 0) {
            resp.sendRedirect(GO_TO_CLIENT_SEARCHING_PAGE + INCORRECT_QUANTITY);
        }
        HttpSession session = req.getSession();
        OrderDto orderDto = (OrderDto) session.getAttribute(PARAM_NAME_ORDER_DTO);
        Storage storage = (Storage) session.getAttribute(PARAM_NAME_STORAGE);
        if (orderDto == null) {
            orderDto = new OrderDto();
            Map<Storage, Integer> goods = new HashMap<>();
            goods.put(storage, quantity);
            orderDto.setGoods(goods);
            session.setAttribute(PARAM_NAME_ORDER_DTO, orderDto);
            resp.sendRedirect(GO_TO_CLIENT_SEARCHING_PAGE + PARAM_NAME_OPERATION_RESULT + "=" +
                    PARAM_VALUE_OPERATION_RESULT_SUCCESS);
        } else if (!orderDto.getGoods().containsKey(storage)) {
            orderDto.getGoods().put(storage, quantity);
            resp.sendRedirect(GO_TO_CLIENT_SEARCHING_PAGE + PARAM_NAME_OPERATION_RESULT + "=" +
                    PARAM_VALUE_OPERATION_RESULT_SUCCESS);
        } else {
            resp.sendRedirect(GO_TO_CLIENT_SEARCHING_PAGE + PARAM_NAME_OPERATION_RESULT + "=" +
                    PARAM_VALUE_GOOD_ALREADY_IN_BASKET);
        }
    }
}

