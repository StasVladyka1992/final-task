package by.vladyka.epam.controller.impl.doctor;

import by.vladyka.epam.controller.Command;
import by.vladyka.epam.entity.User;
import by.vladyka.epam.service.ServiceProvider;
import by.vladyka.epam.service.exception.ServiceException;
import by.vladyka.epam.service.impl.ReceiptServiceImpl;
import by.vladyka.epam.tdo.EntitySearchingResult;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.vladyka.epam.controller.util.JSPNavigation.GO_TO_PRESCRIPTION_LIST;
import static by.vladyka.epam.controller.util.JSPNavigation.GO_TO_REJECTION_LIST;
import static by.vladyka.epam.controller.util.Pagination.*;
import static by.vladyka.epam.controller.util.Pagination.setSessionPaginationParams;
import static by.vladyka.epam.controller.util.ParameterName.PARAM_NAME_RECEIPT_LIST;
import static by.vladyka.epam.controller.util.ParameterName.PARAM_NAME_USER;

/**
 * Created by Vladyka Stas
 * on 18.03.2019 at 15:45
 **/
public class ShowWrittenPrescription implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException, IOException, ServletException {
        HttpSession session = req.getSession(true);
        User user = (User) session.getAttribute(PARAM_NAME_USER);
        int currentPage = getCurrentPage(req);
        int doctotrId = user.getId();
        int startPosition = calculateStartPosition(currentPage);
        EntitySearchingResult entitySearchingResult;
        ServiceProvider provider = ServiceProvider.getInstance();
        ReceiptServiceImpl service = provider.getReceiptService();
        entitySearchingResult = service.findWrittenPrescriptions(doctotrId, startPosition, OFFSET);
        setSessionPaginationParams(session, currentPage, entitySearchingResult, PARAM_NAME_RECEIPT_LIST);
        resp.sendRedirect(GO_TO_PRESCRIPTION_LIST);
    }
}
