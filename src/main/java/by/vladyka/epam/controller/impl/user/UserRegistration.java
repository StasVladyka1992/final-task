package by.vladyka.epam.controller.impl.user;

import by.vladyka.epam.controller.Command;
import by.vladyka.epam.entity.User;
import by.vladyka.epam.service.ServiceProvider;
import by.vladyka.epam.service.UserService;
import by.vladyka.epam.service.exception.ServiceException;
import by.vladyka.epam.service.impl.UserServiceImpl;
import by.vladyka.epam.service.validator.impl.UserValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.vladyka.epam.controller.util.JSPNavigation.GO_TO_REGISTRATION;
import static by.vladyka.epam.controller.util.JSPNavigation.GO_TO_REGISTRATION_RESULT;
import static by.vladyka.epam.controller.util.ParameterName.*;

/**
 * Created by Vladyka Stas
 * on 17.02.2019 at 13:25
 **/
public class UserRegistration implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException, IOException {
        String email = req.getParameter(PARAM_NAME_EMAIL);
        String password = req.getParameter(PARAM_NAME_PASSWORD);
        String firstName = req.getParameter(PARAM_NAME_FIRST_NAME);
        String lastName = req.getParameter(PARAM_NAME_LAST_NAME);
        String phone = req.getParameter(PARAM_NAME_PHONE);
        User.UserRole role = User.UserRole.valueOf(req.getParameter(PARAM_NAME_ROLE));

        UserService<User> userService = ServiceProvider.getInstance().getUserService();
        boolean isRegistrationSuccessful = userService.registration(email, firstName, lastName, password, phone, role);

        HttpSession session = req.getSession(true);
        rememberLastPage(req);
        if (isRegistrationSuccessful) {
            User user = new User();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setRole(role);
            user.setPhone(phone);
            session.setAttribute(PARAM_NAME_USER, user);
            resp.sendRedirect(GO_TO_REGISTRATION_RESULT);
        } else {
            UserValidator validator = ((UserServiceImpl) userService).getValidator();
            String invalidDataMessages = validator.getIncorrectDataMessages().toString();
            resp.sendRedirect(GO_TO_REGISTRATION + invalidDataMessages);
        }
    }
}
