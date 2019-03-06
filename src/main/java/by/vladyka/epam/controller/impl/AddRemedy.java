package by.vladyka.epam.controller.impl;

import by.vladyka.epam.controller.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
//        ServiceProvider creator = ServiceProvider.getInstance();
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
