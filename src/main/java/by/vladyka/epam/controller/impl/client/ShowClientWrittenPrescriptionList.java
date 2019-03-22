package by.vladyka.epam.controller.impl.client;

import by.vladyka.epam.controller.Command;
import by.vladyka.epam.dto.EntitySearchingResult;
import by.vladyka.epam.entity.User;
import by.vladyka.epam.service.ServiceProvider;
import by.vladyka.epam.service.exception.ServiceException;
import by.vladyka.epam.service.impl.ReceiptServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.vladyka.epam.controller.util.JSPNavigation.GO_TO_CLIENT_WRITTEN_PRESCRIPTION_LIST;
import static by.vladyka.epam.controller.util.JSPNavigation.GO_TO_PRESCRIPTION_APPLICATION_LIST;
import static by.vladyka.epam.controller.util.Pagination.*;
import static by.vladyka.epam.controller.util.Pagination.setSessionPaginationParams;
import static by.vladyka.epam.controller.util.ParameterName.PARAM_NAME_RECEIPT_LIST;
import static by.vladyka.epam.controller.util.ParameterName.PARAM_NAME_USER;

/**
 * Created by Vladyka Stas
 * on 21.03.2019 at 23:42
 **/
public class ShowClientWrittenPrescriptionList implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException, IOException, ServletException {
        ServiceProvider provider = ServiceProvider.getInstance();
        ReceiptServiceImpl service = provider.getReceiptService();
        HttpSession session = req.getSession();
        int currentPage = getCurrentPage(req);
        int startPosition = calculateStartPosition(currentPage);
        User user = (User) session.getAttribute(PARAM_NAME_USER);
        EntitySearchingResult entitySearchingResult;
        entitySearchingResult = service.findClientWrittenPrescriptions(user.getId(), startPosition, OFFSET);
        setSessionPaginationParams(session, currentPage, entitySearchingResult, PARAM_NAME_RECEIPT_LIST);
        resp.sendRedirect(GO_TO_CLIENT_WRITTEN_PRESCRIPTION_LIST);
    }
}
