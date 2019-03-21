package by.vladyka.epam.controller.impl.doctor;

import by.vladyka.epam.controller.Command;
import by.vladyka.epam.service.ServiceProvider;
import by.vladyka.epam.service.exception.ServiceException;
import by.vladyka.epam.service.impl.ReceiptServiceImpl;
import by.vladyka.epam.dto.EntitySearchingResult;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.vladyka.epam.controller.util.JSPNavigation.GO_TO_PRESCRIPTION_APPLICATION;
import static by.vladyka.epam.controller.util.Pagination.*;
import static by.vladyka.epam.controller.util.ParameterName.*;

/**
 * Created by Vladyka Stas
 * on 16.03.2019 at 15:01
 **/
public class ShowUnhandledApplication implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException, IOException, ServletException {
        ServiceProvider provider = ServiceProvider.getInstance();
        ReceiptServiceImpl service = provider.getReceiptService();
        int currentPage = getCurrentPage(req);
        int startPosition = calculateStartPosition(currentPage);
        EntitySearchingResult entitySearchingResult;
        entitySearchingResult = service.findUnhandledReceipts(startPosition, OFFSET);
        HttpSession session = req.getSession(true);
        setSessionPaginationParams(session, currentPage, entitySearchingResult, PARAM_NAME_RECEIPT_LIST);
        String operationResult = req.getParameter(PARAM_NAME_OPERATION_RESULT);
        if (operationResult != null) {
            resp.sendRedirect(GO_TO_PRESCRIPTION_APPLICATION + PARAM_NAME_OPERATION_RESULT + "=" + operationResult);
        } else {
            resp.sendRedirect(GO_TO_PRESCRIPTION_APPLICATION);
        }
    }
}
