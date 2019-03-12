package by.vladyka.epam.controller.impl.pharmacist;

import by.vladyka.epam.controller.Command;
import by.vladyka.epam.entity.RemedySearchingResult;
import by.vladyka.epam.service.RemedyService;
import by.vladyka.epam.service.ServiceProvider;
import by.vladyka.epam.service.exception.ServiceException;

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
public class FindRemedy implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException, IOException {
        //TODO переписать на поиск со склада, не находит все позиции, потому что inner join cо складом
        String remedyName = saveSearchingName(req, REMEDY_NAME);
        int currentPage = Integer.parseInt(req.getParameter(CURRENT_PAGE));
        int startPosition = calculateStartPosition(currentPage);

        ServiceProvider creator = ServiceProvider.getInstance();
        RemedySearchingResult remedySearchingResult;
        RemedyService service = creator.getRemedyService();
        remedySearchingResult = service.findFromStartPosition(remedyName, startPosition, OFFSET);

        HttpSession session = req.getSession(true);
        rememberLastPage(req);
        session.setAttribute(REMEDY_NAME, remedyName);
        session.setAttribute(CURRENT_PAGE, currentPage);
        session.setAttribute(REMEDY_LIST, remedySearchingResult.getRemedies());
        session.setAttribute(PAGES_NUMBER, calculatePagesNumber(remedySearchingResult));
        resp.sendRedirect(GO_TO_REMEDY);
    }
}
