package by.vladyka.epam.controller.impl;

import by.vladyka.epam.controller.Command;
import by.vladyka.epam.controller.util.URLRestorer;
import by.vladyka.epam.entity.RemedySearchingResult;
import by.vladyka.epam.service.ServiceCreator;
import by.vladyka.epam.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.vladyka.epam.controller.util.JSPNavigation.GO_TO_REMEDY;
import static by.vladyka.epam.controller.util.Pagination.*;
import static by.vladyka.epam.controller.util.Pagination.calculatePagesNumber;

/**
 * Created by Vladyka Stas
 * on 05.03.2019 at 2:37
 **/
public class AddRemedy implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        int currentPage = Integer.parseInt(req.getParameter("currentPage"));
//        int startPosition = calculateStartPosition(currentPage);
//        RemedySearchingResult remedySearchingResult;
//        ServiceCreator creator = ServiceCreator.getInstance();
//        try {
//            remedySearchingResult = creator.getRemedyService().find(remedyName, startPosition, OFFSET);
//        } catch (ServiceException ex) {
//            //TODO logger перебросить на какую-либо страницу, например на дефолтную
//            throw new ServletException(ex);
//        }
//        HttpSession session = req.getSession(true);
//        String url = URLRestorer.restoreURL(req);
//        session.setAttribute("previous_url", url);
//        session.setAttribute("remedyName", remedyName);
//        session.setAttribute("currentPage",currentPage);
//        session.setAttribute("remedyList", remedySearchingResult.getRemedies());
//        session.setAttribute("pagesNumber", calculatePagesNumber(remedySearchingResult));
//
//        resp.sendRedirect(GO_TO_REMEDY);
    }
}
