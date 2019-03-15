package by.vladyka.epam.controller.impl.pharmacist;

import by.vladyka.epam.controller.Command;
import by.vladyka.epam.service.ServiceProvider;
import by.vladyka.epam.service.StorageService;
import by.vladyka.epam.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.vladyka.epam.controller.util.JSPNavigation.GO_TO_REMEDY_ADMINISTRATION;
import static by.vladyka.epam.controller.util.JSPNavigation.GO_TO_UPDATE_REMEDY;
import static by.vladyka.epam.controller.util.ParameterName.*;
import static by.vladyka.epam.controller.util.ParameterValue.PARAM_VALUE_OPERATION_RESULT_FAIL;
import static by.vladyka.epam.controller.util.ParameterValue.PARAM_VALUE_OPERATION_RESULT_SUCCESS;

/**
 * Created by Vladyka Stas
 * on 11.03.2019 at 18:04
 **/
public class SetStorageQuantity implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException, IOException, ServletException {
        int remedyId = Integer.parseInt(req.getParameter(PARAM_NAME_ID));
        int remedyLeft = Integer.parseInt(req.getParameter(PARAM_NAME_STORAGE_REMEDY_LEFT));
        ServiceProvider provider = ServiceProvider.getInstance();
        StorageService service = provider.getStorageService();
        boolean isOperationSuccessfull = service.update(remedyId, remedyLeft);
        rememberLastPage(req);
        if (isOperationSuccessfull) {
            resp.sendRedirect(GO_TO_REMEDY_ADMINISTRATION+PARAM_NAME_OPERATION_RESULT + "=" + PARAM_VALUE_OPERATION_RESULT_SUCCESS);
        }
        else {
            resp.sendRedirect(GO_TO_REMEDY_ADMINISTRATION+PARAM_NAME_OPERATION_RESULT + "=" + PARAM_VALUE_OPERATION_RESULT_FAIL);
        }
    }
}
