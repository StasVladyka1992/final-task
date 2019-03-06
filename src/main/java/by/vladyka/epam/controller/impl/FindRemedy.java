package by.vladyka.epam.controller.impl;

import by.vladyka.epam.controller.Command;
import by.vladyka.epam.controller.util.URLRestorer;
import by.vladyka.epam.entity.RemedySearchingResult;
import by.vladyka.epam.service.ServiceProvider;
import by.vladyka.epam.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
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
    private static final Logger logger = LogManager.getLogger(FindRemedy.class);
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String remedyName = saveSearchingName(req, REMEDY_NAME);
        int currentPage = Integer.parseInt(req.getParameter(CURRENT_PAGE));
        int startPosition = calculateStartPosition(currentPage);
        RemedySearchingResult remedySearchingResult;
        ServiceProvider creator = ServiceProvider.getInstance();
        try {
            remedySearchingResult = creator.getRemedyService().find(remedyName, startPosition, OFFSET);
        } catch (ServiceException ex) {
            logger.error(ex);
            throw new ServletException();
        }
        HttpSession session = req.getSession(true);
        rememberLastPage(req);
        session.setAttribute(REMEDY_NAME, remedyName);
        session.setAttribute(CURRENT_PAGE,currentPage);
        session.setAttribute(REMEDY_LIST, remedySearchingResult.getRemedies());
        session.setAttribute(PAGES_NUMBER, calculatePagesNumber(remedySearchingResult));

        resp.sendRedirect(GO_TO_REMEDY);
    }



}
