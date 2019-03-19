package by.vladyka.epam.controller.impl.doctor;

import by.vladyka.epam.controller.Command;
import by.vladyka.epam.entity.Receipt;
import by.vladyka.epam.service.ServiceProvider;
import by.vladyka.epam.service.exception.ServiceException;
import by.vladyka.epam.service.impl.ReceiptServiceImpl;
import by.vladyka.epam.service.validator.impl.ReceiptValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static by.vladyka.epam.controller.util.JSPNavigation.*;
import static by.vladyka.epam.controller.util.ParameterName.*;
import static by.vladyka.epam.controller.util.ParameterValue.PARAM_VALUE_PRESCRIPTION_WROTE;

/**
 * Created by Vladyka Stas
 * on 17.03.2019 at 0:30
 **/
public class WritePrescription implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException, IOException, ServletException {
        String message = req.getParameter(PARAM_NAME_MESSAGE);
        String expireDateText = req.getParameter(PARAM_NAME_EXPIRE_DATE);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.sql.Date expireDate;
        try {
            expireDate = new java.sql.Date((dateFormat.parse(expireDateText)).getTime());
        } catch (ParseException e) {
            //TODO правильно пробросить исключение
            throw new ServiceException(e);
        }
        java.sql.Date prescriptionDate = new java.sql.Date(new Date().getTime());
        Receipt.Status status = Receipt.Status.APPROVED;
        int doctorId = Integer.parseInt(req.getParameter(PARAM_NAME_DOCTOR_ID));
        int id = Integer.parseInt(req.getParameter(PARAM_NAME_ID));
        ServiceProvider provider = ServiceProvider.getInstance();
        ReceiptServiceImpl service = provider.getReceiptService();
        boolean result = service.createReceipt(id, doctorId, expireDate, prescriptionDate, message, status);
        ReceiptValidator validator = service.getValidator();
        String url = formNextUrl(result, validator,PARAM_NAME_OPERATION_RESULT, PARAM_VALUE_PRESCRIPTION_WROTE,
                GO_TO_PRESCRIPTION_APPLICATION);
        resp.sendRedirect(url);
    }
}
