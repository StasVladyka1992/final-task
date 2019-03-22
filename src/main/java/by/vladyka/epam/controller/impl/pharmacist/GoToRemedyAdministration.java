package by.vladyka.epam.controller.impl.pharmacist;

import by.vladyka.epam.controller.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.vladyka.epam.controller.util.JSPNavigation.REMEDY_ADMINISTRATION;

/**
 * Created by Vladyka Stas
 * on 03.03.2019 at 23:17
 **/
public class GoToRemedyAdministration implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        rememberLastRequest(req);
        req.getRequestDispatcher(REMEDY_ADMINISTRATION).forward(req,resp);
    }
}
