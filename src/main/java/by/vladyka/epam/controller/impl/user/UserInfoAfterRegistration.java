package by.vladyka.epam.controller.impl.user;

import by.vladyka.epam.controller.Command;
import by.vladyka.epam.controller.util.URLRestorer;
import by.vladyka.epam.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.vladyka.epam.controller.util.JSPNavigation.REGISTRATION_RESULT;
import static by.vladyka.epam.controller.util.ParameterName.PREVIOUS_URL;

/**
 * Created by Vladyka Stas
 * on 19.02.2019 at 23:50
 **/
public class UserInfoAfterRegistration implements Command {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException,ServletException {
        rememberLastPage(req);
        req.getRequestDispatcher(REGISTRATION_RESULT).forward(req, resp);
    }
}
