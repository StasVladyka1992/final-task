package by.vladyka.epam.controller.impl.pharmacist;

import by.vladyka.epam.controller.Command;
import by.vladyka.epam.service.RemedyService;
import by.vladyka.epam.service.ServiceProvider;
import by.vladyka.epam.service.exception.ServiceException;
import by.vladyka.epam.service.impl.RemedyServiceImpl;
import by.vladyka.epam.service.validator.impl.RemedyValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.vladyka.epam.controller.util.JSPNavigation.GO_TO_REMEDY_ADMINISTRATION;
import static by.vladyka.epam.controller.util.JSPNavigation.formNextUrl;
import static by.vladyka.epam.controller.util.ParameterName.*;
import static by.vladyka.epam.controller.util.ParameterValue.PARAM_VALUE_OPERATION_RESULT_SUCCESS;

/**
 * Created by Vladyka Stas
 * on 09.03.2019 at 1:49
 **/
public class DeleteRemedy implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException, IOException {
        int idToDelete = Integer.parseInt(req.getParameter(PARAM_NAME_ID));
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        RemedyService remedyService = serviceProvider.getRemedyService();
        boolean result = remedyService.delete(idToDelete);
        RemedyValidator validator = ((RemedyServiceImpl) remedyService).getValidator();
        String url = formNextUrl(result, validator,PARAM_NAME_OPERATION_RESULT, PARAM_VALUE_OPERATION_RESULT_SUCCESS,
                GO_TO_REMEDY_ADMINISTRATION);
        resp.sendRedirect(url);
    }
}
