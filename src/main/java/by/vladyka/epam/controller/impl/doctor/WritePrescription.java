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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static by.vladyka.epam.controller.util.JSPNavigation.GO_TO_PRESCRIPTION_APPLICATION;
import static by.vladyka.epam.controller.util.JSPNavigation.formNextUrl;
import static by.vladyka.epam.controller.util.ParameterName.*;
import static by.vladyka.epam.controller.util.ParameterValue.PARAM_VALUE_PRESCRIPTION_WROTE;

/**
 * Created by Vladyka Stas
 * on 17.03.2019 at 0:30
 **/
public class WritePrescription implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException, IOException {
        String expireDateText = req.getParameter(PARAM_NAME_EXPIRE_DATE);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date expireDate;
        try {
            expireDate = new Date((dateFormat.parse(expireDateText)).getTime());
        } catch (ParseException e) {
            throw new CommandException(e);
        }
        Date prescriptionDate = new Date();
        Receipt.Status status = Receipt.Status.APPROVED;
        int doctorId = Integer.parseInt(req.getParameter(PARAM_NAME_DOCTOR_ID));
        int id = Integer.parseInt(req.getParameter(PARAM_NAME_ID));
        String message = req.getParameter(PARAM_NAME_MESSAGE);
        ServiceProvider provider = ServiceProvider.getInstance();
        ReceiptServiceImpl service = provider.getReceiptService();
        boolean result;
        try {
            result = service.createReceipt(id, doctorId, expireDate, prescriptionDate, message, status);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        ReceiptValidator validator = service.getValidator();
        String url = formNextUrl(result, validator, PARAM_NAME_OPERATION_RESULT, PARAM_VALUE_PRESCRIPTION_WROTE,
                GO_TO_PRESCRIPTION_APPLICATION);
        resp.sendRedirect(url);
    }
}
