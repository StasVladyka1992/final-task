package by.vladyka.epam.controller.impl;

import by.vladyka.epam.controller.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.vladyka.epam.controller.util.JSPNavigation.REMEDY_MODIFICATION_RESULT;


/**
 * Created by Vladyka Stas
 * on 07.03.2019 at 13:25
 **/
public class GoToRemedyModificationResult implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        rememberLastPage(req);
        req.getRequestDispatcher(REMEDY_MODIFICATION_RESULT).forward(req,resp);
    }
}
