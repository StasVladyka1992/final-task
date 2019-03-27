package by.vladyka.epam.controller.impl.client;

import by.vladyka.epam.controller.Command;
import by.vladyka.epam.controller.exception.CommandException;
import by.vladyka.epam.entity.User;
import by.vladyka.epam.service.ServiceProvider;
import by.vladyka.epam.service.exception.ServiceException;
import by.vladyka.epam.service.impl.ReceiptServiceImpl;
import by.vladyka.epam.service.validator.impl.ReceiptValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.vladyka.epam.controller.util.JSPNavigation.GO_TO_CLIENT_SEARCHING_PAGE;
import static by.vladyka.epam.controller.util.JSPNavigation.formNextUrl;
import static by.vladyka.epam.controller.util.ParameterName.*;
import static by.vladyka.epam.controller.util.ParameterValue.PARAM_VALUE_APPLICATION_ACCEPTED;

/**
 * Created by Vladyka Stas
 * on 15.03.2019 at 11:28
 **/
public class AskForPrescription implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException, IOException {
        ServiceProvider provider = ServiceProvider.getInstance();
        ReceiptServiceImpl service = provider.getReceiptService();
        User user = (User) req.getSession().getAttribute(PARAM_NAME_USER);
        int clientId = user.getId();
        int remedyId = Integer.parseInt(req.getParameter(PARAM_NAME_ID));
        boolean result;
        try {
            result = provider.getReceiptService().createAppliance(clientId, remedyId);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        ReceiptValidator validator = service.getValidator();
        String url = formNextUrl(result, validator, PARAM_NAME_OPERATION_RESULT, PARAM_VALUE_APPLICATION_ACCEPTED,
                GO_TO_CLIENT_SEARCHING_PAGE);
        resp.sendRedirect(url);
    }
}
