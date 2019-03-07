package by.vladyka.epam.controller.impl;

import by.vladyka.epam.controller.Command;
import by.vladyka.epam.service.RemedyService;
import by.vladyka.epam.service.ServiceProvider;
import by.vladyka.epam.service.exception.ServiceException;
import by.vladyka.epam.service.impl.RemedyServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

import static by.vladyka.epam.controller.util.JSPNavigation.*;
import static by.vladyka.epam.controller.util.ParameterDataExtractor.extractRemedyAddingParameters;

/**
 * Created by Vladyka Stas
 * on 05.03.2019 at 2:37
 **/
public class AddRemedy implements Command {
    private static final Logger logger = LogManager.getLogger(AddRemedy.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String> remedyData = extractRemedyAddingParameters(req);
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        RemedyService remedyService = serviceProvider.getRemedyService();
        boolean isAddingSuccessful = false;
        try {
            isAddingSuccessful = remedyService.add(remedyData);
        } catch (ServiceException e) {
            logger.error(e);
            //TODO бросить на страницу с ошибкой
        }
        rememberLastPage(req);
        if (!isAddingSuccessful) {
            String incorrectDataMessages = ((RemedyServiceImpl) remedyService).getIncorrectDataMessages();
            //TODO сделать валидацию перед отправкой запроса с помощью js.
            resp.sendRedirect(REMEDY_ADMINISTRATION + incorrectDataMessages);
        } else {
            resp.sendRedirect(GO_TO_REMEDY_MODIFICATION_RESULT);
        }
    }
}
