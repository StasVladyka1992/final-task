package by.vladyka.epam.controller.impl.doctor;

import by.vladyka.epam.controller.Command;
import by.vladyka.epam.controller.exception.CommandException;
import by.vladyka.epam.entity.Receipt;
import by.vladyka.epam.service.ServiceProvider;
import by.vladyka.epam.service.exception.ServiceException;
import by.vladyka.epam.service.impl.ReceiptServiceImpl;
import by.vladyka.epam.service.validator.impl.ReceiptValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.vladyka.epam.controller.util.JSPNavigation.GO_TO_PRESCRIPTION_APPLICATION;
import static by.vladyka.epam.controller.util.JSPNavigation.formNextUrl;
import static by.vladyka.epam.controller.util.ParameterName.*;
import static by.vladyka.epam.controller.util.ParameterValue.PARAM_VALUE_APPLICATION_REJECTED;


/**
 * Created by Vladyka Stas
 * on 17.03.2019 at 2:19
 **/
public class RejectApplication implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException, IOException {
        String message = req.getParameter(PARAM_NAME_MESSAGE);
        int id = Integer.parseInt(req.getParameter(PARAM_NAME_ID));
        int doctorId = Integer.parseInt(req.getParameter(PARAM_NAME_DOCTOR_ID));
        Receipt.Status status = Receipt.Status.REJECTED;
        ReceiptServiceImpl service = ServiceProvider.getInstance().getReceiptService();
        boolean result;
        try {
            result = service.rejectApplication(id, doctorId, message, status);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        ReceiptValidator validator = service.getValidator();
        String url = formNextUrl(result, validator, PARAM_NAME_OPERATION_RESULT, PARAM_VALUE_APPLICATION_REJECTED,
                GO_TO_PRESCRIPTION_APPLICATION);
        resp.sendRedirect(url);
    }
}
