package by.vladyka.epam.controller.impl.pharmacist;

import by.vladyka.epam.controller.Command;
import by.vladyka.epam.controller.exception.CommandException;
import by.vladyka.epam.service.ServiceProvider;
import by.vladyka.epam.service.StorageService;
import by.vladyka.epam.service.exception.ServiceException;
import by.vladyka.epam.service.impl.StorageServiceImpl;
import by.vladyka.epam.service.validator.impl.StorageValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.vladyka.epam.controller.util.JSPNavigation.GO_TO_REMEDY_ADMINISTRATION;
import static by.vladyka.epam.controller.util.JSPNavigation.formNextUrl;
import static by.vladyka.epam.controller.util.ParameterName.*;
import static by.vladyka.epam.controller.util.ParameterValue.PARAM_VALUE_OPERATION_RESULT_SUCCESS;

/**
 * Created by Vladyka Stas
 * on 13.03.2019 at 2:06
 **/
public class AddToStorage implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException, IOException {
        int remedyId = Integer.parseInt(req.getParameter(PARAM_NAME_ID));
        int remedyLeft = Integer.parseInt(req.getParameter(PARAM_NAME_STORAGE_REMEDY_LEFT));
        StorageService service = ServiceProvider.getInstance().getStorageService();
        boolean result;
        try {
            result = service.create(remedyId, remedyLeft);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        StorageValidator validator = ((StorageServiceImpl) service).getValidator();
        String url = formNextUrl(result, validator, PARAM_NAME_OPERATION_RESULT, PARAM_VALUE_OPERATION_RESULT_SUCCESS,
                GO_TO_REMEDY_ADMINISTRATION);
        resp.sendRedirect(url);
    }
}
