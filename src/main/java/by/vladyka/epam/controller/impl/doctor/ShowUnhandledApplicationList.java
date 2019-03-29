package by.vladyka.epam.controller.impl.doctor;

import by.vladyka.epam.controller.Command;
import by.vladyka.epam.controller.exception.CommandException;
import by.vladyka.epam.dto.EntitySearchingResult;
import by.vladyka.epam.service.ServiceProvider;
import by.vladyka.epam.service.exception.ServiceException;
import by.vladyka.epam.service.impl.ReceiptServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static by.vladyka.epam.controller.util.JSPNavigation.GO_TO_PRESCRIPTION_APPLICATION;
import static by.vladyka.epam.controller.util.Pagination.*;
import static by.vladyka.epam.controller.util.ParameterName.PARAM_NAME_RECEIPT_LIST;

/**
 * Created by Vladyka Stas
 * on 16.03.2019 at 15:01
 **/
public class ShowUnhandledApplicationList implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException, IOException {
        ServiceProvider provider = ServiceProvider.getInstance();
        ReceiptServiceImpl service = provider.getReceiptService();
        List unhandledReceipts;
        try {
            unhandledReceipts = service.findUnhandledApplications();
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        HttpSession session = req.getSession();
        session.setAttribute(PARAM_NAME_RECEIPT_LIST, unhandledReceipts);
        resp.sendRedirect(GO_TO_PRESCRIPTION_APPLICATION);
    }
}
