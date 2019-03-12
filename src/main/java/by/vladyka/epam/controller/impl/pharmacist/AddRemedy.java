package by.vladyka.epam.controller.impl.pharmacist;

import by.vladyka.epam.controller.Command;
import by.vladyka.epam.service.RemedyService;
import by.vladyka.epam.service.ServiceProvider;
import by.vladyka.epam.service.exception.ServiceException;
import by.vladyka.epam.service.impl.RemedyServiceImpl;
import by.vladyka.epam.service.validator.impl.RemedyInfoValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.vladyka.epam.controller.util.JSPNavigation.*;
import static by.vladyka.epam.controller.util.ParameterName.*;
import static by.vladyka.epam.controller.util.ParameterValue.REMEDY_ADD_RESULT_SUCCESS;

/**
 * Created by Vladyka Stas
 * on 05.03.2019 at 2:37
 **/
public class AddRemedy implements Command {
    private static final Logger logger = LogManager.getLogger(AddRemedy.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter(REMEDY_NAME);
        double price = Double.parseDouble(req.getParameter(PRICE));
        boolean receiptRequired = Boolean.parseBoolean(req.getParameter(RECEIPT_REQUIRED));
        String description = req.getParameter(DESCRIPTION);

        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        RemedyService remedyService = serviceProvider.getRemedyService();
        boolean isAddingSuccessful = false;
        try {
            isAddingSuccessful = remedyService.add(name, description, price, receiptRequired);
        } catch (ServiceException e) {
            //TODO перебросить на страницу с ошибкой
            logger.error(e);
        }
        rememberLastPage(req);
        if (!isAddingSuccessful) {
            RemedyInfoValidator validator = ((RemedyServiceImpl) remedyService).getRemedyInfoValidator();
            String incorrectDataMessages = validator.getIncorrectDataMessages().toString();
            resp.sendRedirect(GO_TO_REMEDY_ADMINISTRATION + incorrectDataMessages);
        } else {
            resp.sendRedirect(GO_TO_REMEDY_ADMINISTRATION +REMEDY_ADDING_RESULT+"="+ REMEDY_ADD_RESULT_SUCCESS);
        }
    }
}
