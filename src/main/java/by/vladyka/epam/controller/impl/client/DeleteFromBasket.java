package by.vladyka.epam.controller.impl.client;

import by.vladyka.epam.controller.Command;
import by.vladyka.epam.dto.OrderDto;
import by.vladyka.epam.entity.Storage;
import by.vladyka.epam.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import static by.vladyka.epam.controller.util.JSPNavigation.GO_TO_BASKET;
import static by.vladyka.epam.controller.util.ParameterName.*;
import static by.vladyka.epam.controller.util.ParameterValue.PARAM_VALUE_REMEDY_DELETED;

/**
 * Created by Vladyka Stas
 * on 20.03.2019 at 16:13
 **/
public class DeleteFromBasket implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException, IOException, ServletException {
        HttpSession session = req.getSession();
        OrderDto orderDto = (OrderDto) session.getAttribute(PARAM_NAME_ORDER_DTO);
        int id = Integer.parseInt(req.getParameter(PARAM_NAME_ID));
        Iterator<Map.Entry<Storage, Integer>> iter = orderDto.getGoods().entrySet().iterator();
        while (iter.hasNext()) {
            if (iter.next().getKey().getId() == id) {
                iter.remove();
                break;
            }
        }
        resp.sendRedirect(GO_TO_BASKET+PARAM_NAME_OPERATION_RESULT+"="+PARAM_VALUE_REMEDY_DELETED);
    }
}
