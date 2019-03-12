package by.vladyka.epam.controller.impl.pharmacist;

import by.vladyka.epam.controller.Command;
import by.vladyka.epam.service.ServiceProvider;
import by.vladyka.epam.service.exception.ServiceException;
import by.vladyka.epam.service.impl.RemedyServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.vladyka.epam.controller.util.JSPNavigation.GO_TO_REMEDY_ADMINISTRATION;
import static by.vladyka.epam.controller.util.JSPNavigation.GO_TO_UPDATE_REMEDY;
import static by.vladyka.epam.controller.util.ParameterName.*;
import static by.vladyka.epam.controller.util.ParameterName.DESCRIPTION;
import static by.vladyka.epam.controller.util.ParameterValue.REMEDY_UPDATE_RESULT_SUCCESS;

/**
 * Created by Vladyka Stas
 * on 10.03.2019 at 23:39
 **/
public class UpdateRemedy implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException, IOException, ServletException {
        int id = Integer.parseInt(req.getParameter(ID));
        String name = req.getParameter(REMEDY_NAME);
        double price = Double.parseDouble(req.getParameter(PRICE));
        boolean receiptRequired = Boolean.parseBoolean(req.getParameter(RECEIPT_REQUIRED));
        String description = req.getParameter(DESCRIPTION);

        ServiceProvider provider = ServiceProvider.getInstance();
        RemedyServiceImpl service = provider.getRemedyService();
        boolean isUpdateSuccessfull = service.update(id, name, description, price, receiptRequired);
        if (!isUpdateSuccessfull) {
            String incorrectDataMessages= service.getRemedyInfoValidator().getIncorrectDataMessages().toString();
            resp.sendRedirect(GO_TO_REMEDY_ADMINISTRATION + incorrectDataMessages);
        } else {
            resp.sendRedirect(GO_TO_REMEDY_ADMINISTRATION +REMEDY_ADDING_RESULT+"="+REMEDY_UPDATE_RESULT_SUCCESS);
        }

    }
}
