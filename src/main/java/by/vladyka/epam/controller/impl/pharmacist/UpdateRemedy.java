package by.vladyka.epam.controller.impl.pharmacist;

import by.vladyka.epam.controller.Command;
import by.vladyka.epam.service.ServiceProvider;
import by.vladyka.epam.service.exception.ServiceException;
import by.vladyka.epam.service.impl.RemedyServiceImpl;
import by.vladyka.epam.service.validator.impl.RemedyValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.vladyka.epam.controller.util.JSPNavigation.GO_TO_REMEDY_ADMINISTRATION;
import static by.vladyka.epam.controller.util.JSPNavigation.formNextUrl;
import static by.vladyka.epam.controller.util.ParameterName.*;
import static by.vladyka.epam.controller.util.ParameterValue.PARAM_VALUE_OPERATION_RESULT_SUCCESS;

/**
 * Created by Vladyka Stas
 * on 10.03.2019 at 23:39
 **/
public class UpdateRemedy implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException, IOException, ServletException {
        int id = Integer.parseInt(req.getParameter(PARAM_NAME_ID));
        String name = req.getParameter(PARAM_NAME_REMEDY_NAME);
        double price = Double.parseDouble(req.getParameter(PARAM_NAME_PRICE));
        boolean receiptRequired = Boolean.parseBoolean(req.getParameter(PARAM_NAME_RECEIPT_REQUIRED));
        String description = req.getParameter(PARAM_NAME_DESCRIPTION);
        ServiceProvider provider = ServiceProvider.getInstance();
        RemedyServiceImpl service = provider.getRemedyService();
        boolean result = service.update(id, name, description, price, receiptRequired);
        RemedyValidator validator = service.getValidator();
        String url = formNextUrl(result, validator, PARAM_NAME_OPERATION_RESULT, PARAM_VALUE_OPERATION_RESULT_SUCCESS,
                GO_TO_REMEDY_ADMINISTRATION);
        resp.sendRedirect(url);
    }
}
