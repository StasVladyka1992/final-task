package by.vladyka.epam.controller.impl.pharmacist;

import by.vladyka.epam.controller.Command;
import by.vladyka.epam.controller.exception.CommandException;
import by.vladyka.epam.dto.EntitySearchingResult;
import by.vladyka.epam.service.ClientOrderService;
import by.vladyka.epam.service.ServiceProvider;
import by.vladyka.epam.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.vladyka.epam.controller.util.JSPNavigation.GO_TO_PURCHASE_ADMINISTRATION;
import static by.vladyka.epam.controller.util.Pagination.*;
import static by.vladyka.epam.controller.util.ParameterName.PARAM_NAME_CLIENT_ORDER_LIST;

/**
 * Created by Vladyka Stas
 * on 22.03.2019 at 0:02
 **/
public class ShowUnhandledOrderList implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException, IOException {
        ServiceProvider provider = ServiceProvider.getInstance();
        ClientOrderService service = provider.getClientOrderService();
        int currentPage = getCurrentPage(req);
        int startPosition = calculateStartPosition(currentPage);
        EntitySearchingResult entitySearchingResult;
        try {
            entitySearchingResult = service.findUnhandledOrders(startPosition, OFFSET);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        HttpSession session = req.getSession();
        setSessionPaginationParams(session, currentPage, entitySearchingResult, PARAM_NAME_CLIENT_ORDER_LIST);
        resp.sendRedirect(GO_TO_PURCHASE_ADMINISTRATION);
    }
}
