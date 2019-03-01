package by.vladyka.epam.controller.impl;

import by.vladyka.epam.controller.Command;
import by.vladyka.epam.controller.util.ParameterDataExtractor;
import by.vladyka.epam.controller.util.URLRestorer;
import by.vladyka.epam.entity.User;
import by.vladyka.epam.service.ServiceCreator;
import by.vladyka.epam.service.UserService;
import by.vladyka.epam.service.exception.ServiceException;
import by.vladyka.epam.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

import static by.vladyka.epam.controller.util.JSPNavigation.GO_TO_REGISTRATION;
import static by.vladyka.epam.controller.util.JSPNavigation.GO_TO_REGISTRATION_RESULT;

/**
 * Created by Vladyka Stas
 * on 17.02.2019 at 13:25
 **/
public class UserRegistration implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = URLRestorer.restoreURL(req);
        HttpSession session = req.getSession(true);
        session.setAttribute("previous_url", url);

        Map<String, String> parameters = ParameterDataExtractor.extractUserRegistrationParameters(req);
        UserService<User> userService = ServiceCreator.getInstance().getUserService();
        boolean isRegistrationSuccessful;
        try {
            isRegistrationSuccessful = userService.registration(parameters);
        } catch (ServiceException e) {
            //TODO logger.error
            throw new ServletException(e);
        }
        if (isRegistrationSuccessful) {
            User user = new User();
            user.setFirstName(parameters.get("firstName"));
            user.setLastName(parameters.get("lastName"));
            user.setEmail(parameters.get("email"));
            user.setRole(parameters.get("role").charAt(0));
            session.setAttribute("user", user);
            resp.sendRedirect(GO_TO_REGISTRATION_RESULT);
        } else {
            String invalidDataMessage = ((UserServiceImpl) userService).getIncorrectDataMessages();
            resp.sendRedirect(GO_TO_REGISTRATION + invalidDataMessage);
        }
    }
}
