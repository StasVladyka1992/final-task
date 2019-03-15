package by.vladyka.epam.controller.impl.pharmacist;

import by.vladyka.epam.controller.Command;
import by.vladyka.epam.entity.Remedy;
import by.vladyka.epam.entity.Storage;
import by.vladyka.epam.service.RemedyService;
import by.vladyka.epam.service.ServiceProvider;
import by.vladyka.epam.service.StorageService;
import by.vladyka.epam.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.vladyka.epam.controller.util.JSPNavigation.GO_TO_REMEDY_ADMINISTRATION;
import static by.vladyka.epam.controller.util.JSPNavigation.UPDATE_REMEDY;
import static by.vladyka.epam.controller.util.ParameterName.*;
import static by.vladyka.epam.controller.util.ParameterValue.PARAM_VALUE_REMEDY_NOT_EXIST;

/**
 * Created by Vladyka Stas
 * on 11.03.2019 at 10:58
 **/
public class GoToUpdateRemedy implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException, IOException, ServletException {
        int id = Integer.parseInt(req.getParameter(PARAM_NAME_ID));
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        StorageService service = serviceProvider.getStorageService();
        Storage storage = service.findById(id);
        rememberLastPage(req);
        if (storage == null) {
            resp.sendRedirect(GO_TO_REMEDY_ADMINISTRATION+PARAM_NAME_OPERATION_RESULT+"="+PARAM_VALUE_REMEDY_NOT_EXIST);
        }
        else {
            req.setAttribute(PARAM_NAME_STORAGE, storage);
            req.getRequestDispatcher(UPDATE_REMEDY).forward(req, resp);
        }
    }
}
