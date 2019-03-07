package by.vladyka.epam.controller.impl;

import by.vladyka.epam.controller.Command;
import by.vladyka.epam.controller.util.ParameterDataExtractor;
import by.vladyka.epam.controller.util.URLRestorer;
import by.vladyka.epam.entity.User;
import by.vladyka.epam.service.ServiceProvider;
import by.vladyka.epam.service.UserService;
import by.vladyka.epam.service.exception.ServiceException;
import by.vladyka.epam.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

import static by.vladyka.epam.controller.util.JSPNavigation.GO_TO_REGISTRATION;
import static by.vladyka.epam.controller.util.JSPNavigation.GO_TO_REGISTRATION_RESULT;
import static by.vladyka.epam.controller.util.ParameterName.*;

/**
 * Created by Vladyka Stas
 * on 17.02.2019 at 13:25
 **/
public class UserRegistration implements Command {
    private static final Logger logger = LogManager.getLogger(UserRegistration.class);
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String> parameters = ParameterDataExtractor.extractUserRegistrationParameters(req);
        UserService<User> userService = ServiceProvider.getInstance().getUserService();
        boolean isRegistrationSuccessful;
        try {
            isRegistrationSuccessful = userService.registration(parameters);
        } catch (ServiceException e) {
            logger.error(e);
            throw new ServletException();
        }
        HttpSession session = req.getSession(true);
        rememberLastPage(req);
        if (isRegistrationSuccessful) {
            User user = new User();
            user.setFirstName(parameters.get(FIRST_NAME));
            user.setLastName(parameters.get(LAST_NAME));
            user.setEmail(parameters.get(EMAIL));
            user.setRole(parameters.get(ROLE).charAt(0));
            session.setAttribute(USER, user);
            resp.sendRedirect(GO_TO_REGISTRATION_RESULT);
        } else {
            String invalidDataMessage = ((UserServiceImpl) userService).getIncorrectDataMessages();
            resp.sendRedirect(GO_TO_REGISTRATION + invalidDataMessage);
        }
    }
}
