package by.vladyka.epam.controller.impl.user;

import by.vladyka.epam.controller.Command;
import by.vladyka.epam.controller.exception.CommandException;
import by.vladyka.epam.entity.User;
import by.vladyka.epam.service.ServiceProvider;
import by.vladyka.epam.service.exception.ServiceException;
import by.vladyka.epam.service.impl.UserServiceImpl;
import by.vladyka.epam.service.validator.impl.UserValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

import static by.vladyka.epam.controller.util.JSPNavigation.GO_TO_REGISTRATION;
import static by.vladyka.epam.controller.util.JSPNavigation.GO_TO_REGISTRATION_RESULT;
import static by.vladyka.epam.controller.util.ParameterName.*;
import static by.vladyka.epam.controller.util.ParameterValue.getUserInfo;

/**
 * Created by Vladyka Stas
 * on 17.02.2019 at 13:25
 **/
public class UserRegistration implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException, IOException {
        Map<String, String> userInfo = getUserInfo(req);
        String password = req.getParameter(PARAM_NAME_PASSWORD);
        User.UserRole role = User.UserRole.valueOf(req.getParameter(PARAM_NAME_ROLE));
        UserServiceImpl userService = ServiceProvider.getInstance().getUserService();
        boolean isRegistrationSuccessful;
        try {
            isRegistrationSuccessful = userService.registration(userInfo, password, role);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        HttpSession session = req.getSession();
        if (isRegistrationSuccessful) {
            User user = new User();
            user.setFirstName(userInfo.get(PARAM_NAME_FIRST_NAME));
            user.setLastName(userInfo.get(PARAM_NAME_LAST_NAME));
            user.setEmail(userInfo.get(PARAM_NAME_EMAIL));
            user.setPhone(userInfo.get(PARAM_NAME_PHONE));
            user.setRole(role);
            session.setAttribute(PARAM_NAME_USER, user);
            resp.sendRedirect(GO_TO_REGISTRATION_RESULT);
        } else {
            UserValidator validator = userService.getValidator();
            String invalidDataMessages = validator.getIncorrectDataMessages().toString();
            resp.sendRedirect(GO_TO_REGISTRATION + invalidDataMessages);
        }
    }
}
