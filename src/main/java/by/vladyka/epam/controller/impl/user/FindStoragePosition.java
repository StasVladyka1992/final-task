package by.vladyka.epam.controller.impl.user;

import by.vladyka.epam.controller.Command;
import by.vladyka.epam.service.ServiceProvider;
import by.vladyka.epam.service.StorageService;
import by.vladyka.epam.service.exception.ServiceException;
import by.vladyka.epam.dto.EntitySearchingResult;

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
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String remedyName = saveSearchingName(req, PARAM_NAME_REMEDY_NAME);
        int currentPage = Integer.parseInt(req.getParameter(PARAM_NAME_CURRENT_PAGE));
        int startPosition = calculateStartPosition(currentPage);
        ServiceProvider creator = ServiceProvider.getInstance();
        EntitySearchingResult entitySearchingResult = null;
        StorageService service = creator.getStorageService();
        try {
            entitySearchingResult = service.findFromStartPosition(remedyName, startPosition, OFFSET);
        } catch (ServiceException e) {
            //TODO логгер
        }
        HttpSession session = req.getSession(true);
        rememberLastRequest(req);
        session.setAttribute("name", remedyName);
        setSessionPaginationParams(session, currentPage, entitySearchingResult, PARAM_NAME_STORAGE_LIST);
        resp.sendRedirect(GO_TO_REMEDY);
    }
}
