package by.vladyka.epam.controller.impl.pharmacist;

import by.vladyka.epam.controller.Command;
import by.vladyka.epam.service.RemedyService;
import by.vladyka.epam.service.ServiceProvider;
import by.vladyka.epam.service.exception.ServiceException;
import by.vladyka.epam.service.impl.RemedyServiceImpl;
import by.vladyka.epam.service.validator.impl.RemedyInfoValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.vladyka.epam.controller.util.JSPNavigation.FIND_REMEDY;
import static by.vladyka.epam.controller.util.JSPNavigation.GO_TO_REMEDY_ADMINISTRATION;
import static by.vladyka.epam.controller.util.ParameterName.*;
import static by.vladyka.epam.controller.util.ParameterValue.REMEDY_DELETING_RESULT_SUCCESS;

/**
 * Created by Vladyka Stas
 * on 09.03.2019 at 1:49
 **/
public class DeleteRemedy implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException, IOException {
        int idToDelete = Integer.parseInt(req.getParameter(ID));
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        RemedyService remedyService = serviceProvider.getRemedyService();
        boolean isDeletingSuccessfull = remedyService.delete(idToDelete);
        rememberLastPage(req);
        if (!isDeletingSuccessfull) {
            RemedyInfoValidator validator = ((RemedyServiceImpl) remedyService).getRemedyInfoValidator();
            String incorrectMessages = validator.getIncorrectDataMessages().toString();
            resp.sendRedirect(GO_TO_REMEDY_ADMINISTRATION + incorrectMessages);
        } else {
            req.getSession().removeAttribute("remedyList");
            resp.sendRedirect( GO_TO_REMEDY_ADMINISTRATION+ REMEDY_DELETING_RESULT + '=' + REMEDY_DELETING_RESULT_SUCCESS);
        }
    }
}
