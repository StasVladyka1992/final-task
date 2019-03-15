package by.vladyka.epam.controller.impl.user;

import by.vladyka.epam.controller.Command;
import by.vladyka.epam.entity.RemedySearchingResult;
import by.vladyka.epam.service.ServiceProvider;
import by.vladyka.epam.service.StorageService;
import by.vladyka.epam.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

import static by.vladyka.epam.controller.util.JSPNavigation.GO_TO_REMEDY;
import static by.vladyka.epam.controller.util.Pagination.*;
import static by.vladyka.epam.controller.util.ParameterName.*;

/**
 * Created by Vladyka Stas
 * on 26.02.2019 at 13:21
 **/
public class FindRemedyAndStorageInfo implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String remedyName = saveSearchingName(req, PARAM_NAME_REMEDY_NAME);
        int currentPage = Integer.parseInt(req.getParameter(PARAM_NAME_CURRENT_PAGE));
        int startPosition = calculateStartPosition(currentPage);

        ServiceProvider creator = ServiceProvider.getInstance();
        RemedySearchingResult remedySearchingResult = null;
        StorageService service = creator.getStorageService();
        try {
            remedySearchingResult = service.findFromStartPosition(remedyName, startPosition, OFFSET);
        } catch (ServiceException e) {
            //TODO логгер
        }
        HttpSession session = req.getSession(true);
        rememberLastPage(req);
        session.setAttribute(PARAM_NAME_REMEDY_NAME, remedyName);
        session.setAttribute(PARAM_NAME_CURRENT_PAGE, currentPage);
        session.setAttribute(PARAM_NAME_STORAGE_LIST, remedySearchingResult.getPositions());
        session.setAttribute(PARAM_NAME_PAGES_NUMBER, calculatePagesNumber(remedySearchingResult));
        resp.sendRedirect(GO_TO_REMEDY);
    }
}
