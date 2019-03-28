package by.vladyka.epam.controller.impl.user;

import by.vladyka.epam.controller.Command;
import by.vladyka.epam.controller.exception.CommandException;
import by.vladyka.epam.dto.EntitySearchingResult;
import by.vladyka.epam.service.ServiceProvider;
import by.vladyka.epam.service.StorageService;
import by.vladyka.epam.service.exception.ServiceException;
import by.vladyka.epam.service.impl.StorageServiceImpl;
import by.vladyka.epam.service.validator.impl.StorageValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.vladyka.epam.controller.util.JSPNavigation.GO_TO_REMEDY;
import static by.vladyka.epam.controller.util.Pagination.*;
import static by.vladyka.epam.controller.util.ParameterName.*;

/**
 * Created by Vladyka Stas
 * on 26.02.2019 at 13:21
 **/
public class FindStoragePosition implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException, IOException {
        String remedyName = saveSearchingName(req, PARAM_NAME_REMEDY_NAME);
        int currentPage = Integer.parseInt(req.getParameter(PARAM_NAME_CURRENT_PAGE));
        int startPosition = calculateStartPosition(currentPage);
        ServiceProvider creator = ServiceProvider.getInstance();
        StorageService service = creator.getStorageService();
        EntitySearchingResult entitySearchingResult;
        try {
            entitySearchingResult = service.findFromStartPosition(remedyName, startPosition, OFFSET);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        if (entitySearchingResult == null) {
            StorageValidator validator = ((StorageServiceImpl) service).getValidator();
            String incorrectMessage = validator.getIncorrectDataMessages().toString();
            resp.sendRedirect(GO_TO_REMEDY + incorrectMessage);
        } else {
            HttpSession session = req.getSession();
            session.setAttribute(PARAM_NAME_REMEDY_NAME, remedyName);
            setSessionPaginationParams(session, currentPage, entitySearchingResult, PARAM_NAME_STORAGE_LIST);
            resp.sendRedirect(GO_TO_REMEDY);
        }
    }
}
