package by.vladyka.epam.controller.impl;

import by.vladyka.epam.controller.Command;
import by.vladyka.epam.controller.util.URLRestorer;
import by.vladyka.epam.entity.Remedy;
import by.vladyka.epam.service.ServiceCreator;
import by.vladyka.epam.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static by.vladyka.epam.controller.util.JSPNavigation.GO_TO_REMEDY;

/**
 * Created by Vladyka Stas
 * on 26.02.2019 at 13:21
 **/
public class FindRemedy implements Command {
    private static final int OFFSET = 10;
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        String url = URLRestorer.restoreURL(req);
        session.setAttribute("previous_url", url);

        //TODO где хранить параметры start?
        String name = req.getParameter("name");
        String startParameter = req.getParameter("start");

        //TODO подумать над этим участком кода
        int start;
        if (startParameter == null) {
            start = 0;
        } else {
            start = Integer.valueOf(startParameter);
        }
        List<Remedy> remedyList;
        try {
            remedyList = ServiceCreator.getInstance().getRemedyService().find(name, start, OFFSET);
        } catch (ServiceException ex) {
            //TODO logger перебросить на какую-либо страницу, например на дефолтную
            throw new ServletException(ex);
        }
        //Ложить в сессию или в req??
        session.setAttribute("remedyList", remedyList);
        resp.sendRedirect(GO_TO_REMEDY);
    }
}
