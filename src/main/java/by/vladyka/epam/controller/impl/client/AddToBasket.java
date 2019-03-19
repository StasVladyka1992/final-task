package by.vladyka.epam.controller.impl.client;

import by.vladyka.epam.controller.Command;
import by.vladyka.epam.entity.ClientOrder;
import by.vladyka.epam.entity.Remedy;
import by.vladyka.epam.entity.RemedyOrder;
import by.vladyka.epam.entity.Storage;
import by.vladyka.epam.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

import static by.vladyka.epam.controller.util.JSPNavigation.GO_TO_CLIENT_SEARCHING_PAGE;
import static by.vladyka.epam.controller.util.ParameterName.*;
import static by.vladyka.epam.controller.util.ParameterValue.PARAM_VALUE_GOOD_ADDED;
import static by.vladyka.epam.controller.util.ParameterValue.PARAM_VALUE_OPERATION_RESULT_SUCCESS;

/**
 * Created by Vladyka Stas
 * on 15.03.2019 at 0:17
 **/
public class AddToBasket implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException, IOException, ServletException {
        /*
        1. Проверка, есть ли в сессии объект ClientOrder
          - нет - создаем, можно передать объект User для внесения в бд
        2. Создаем объект RemedyOrder и добавляем его в список remedyOrders, принадлежащий ClientOrder
        3.
         */
        HttpSession session = req.getSession();
        ClientOrder basket =  (ClientOrder)session.getAttribute("basket");
        if (basket==null){
            basket =  new ClientOrder();
            basket.setRemedyOrders(new ArrayList<>());
        }
        RemedyOrder order = new RemedyOrder();
//        (Storage)session.getAttribute(PARAM_NAME_STORAGE);




        Remedy remedy = new Remedy();
        remedy.setName(req.getParameter(PARAM_NAME_STORAGE_REMEDY_ID));
        remedy.setId(Integer.parseInt(req.getParameter(PARAM_NAME_QUANTITY)));
//        basket.add(remedy);
//
//        session.setAttribute("basket", basket);
//        for (Remedy r:
//             basket) {
//            System.out.println(r.toString());
//        }
//        resp.sendRedirect(GO_TO_CLIENT_SEARCHING_PAGE+PARAM_NAME_OPERATION_RESULT+"="+PARAM_VALUE_GOOD_ADDED
//        );
    }
}
