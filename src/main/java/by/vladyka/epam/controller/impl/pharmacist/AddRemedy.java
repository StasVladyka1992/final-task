package by.vladyka.epam.controller.impl.pharmacist;

import by.vladyka.epam.controller.Command;
import by.vladyka.epam.service.RemedyService;
import by.vladyka.epam.service.ServiceProvider;
import by.vladyka.epam.service.exception.ServiceException;
import by.vladyka.epam.service.impl.RemedyServiceImpl;
import by.vladyka.epam.service.validator.impl.RemedyValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.vladyka.epam.controller.util.JSPNavigation.GO_TO_REMEDY_ADMINISTRATION;
import static by.vladyka.epam.controller.util.ParameterName.*;
import static by.vladyka.epam.controller.util.ParameterValue.PARAM_VALUE_OPERATION_RESULT_SUCCESS;

/**
 * Created by Vladyka Stas
 * on 05.03.2019 at 2:37
 **/
public class AddRemedy implements Command {
    private static final Logger logger = LogManager.getLogger(AddRemedy.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter(PARAM_NAME_REMEDY_NAME);
        double price = Double.valueOf(req.getParameter(PARAM_NAME_PRICE));
        boolean receiptRequired = Boolean.valueOf(req.getParameter(PARAM_NAME_RECEIPT_REQUIRED));
        String description = req.getParameter(PARAM_NAME_DESCRIPTION);

        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        RemedyService remedyService = serviceProvider.getRemedyService();
        boolean isAddingSuccessful = false;
        try {
            isAddingSuccessful = remedyService.create(name, description, price, receiptRequired);
        } catch (ServiceException e) {
            //TODO перебросить на страницу с ошибкой
            logger.error(e);
        }
        rememberLastPage(req);
        if (!isAddingSuccessful) {
            RemedyValidator validator = ((RemedyServiceImpl) remedyService).getRemedyValidator();
            String incorrectDataMessages = validator.getIncorrectDataMessages().toString();
            resp.sendRedirect(GO_TO_REMEDY_ADMINISTRATION + incorrectDataMessages);
        } else {
            resp.sendRedirect(GO_TO_REMEDY_ADMINISTRATION + PARAM_NAME_OPERATION_RESULT + "=" + PARAM_VALUE_OPERATION_RESULT_SUCCESS);
        }
    }
}
