package by.vladyka.epam.controller.impl;

import by.vladyka.epam.controller.Command;
import by.vladyka.epam.controller.util.URLRestorer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.vladyka.epam.controller.util.JSPNavigation.REGISTRATION_RESULT;

/**
 * Created by Vladyka Stas
 * on 19.02.2019 at 23:50
 **/
public class UserInfoAfterRegistration implements Command {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = URLRestorer.restoreURL(req);
        req.getSession(true).setAttribute("previous_url", url);
        req.getRequestDispatcher(REGISTRATION_RESULT).forward(req, resp);
    }
}
