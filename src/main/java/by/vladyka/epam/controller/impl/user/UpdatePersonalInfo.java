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

import static by.vladyka.epam.controller.util.JSPNavigation.GO_TO_PERSONAL_INFO;
import static by.vladyka.epam.controller.util.JSPNavigation.formNextUrl;
import static by.vladyka.epam.controller.util.ParameterName.*;
import static by.vladyka.epam.controller.util.ParameterValue.PARAM_VALUE_OPERATION_RESULT_SUCCESS;
import static by.vladyka.epam.controller.util.ParameterValue.getUserInfo;

/**
 * Created by Vladyka Stas
 * on 19.03.2019 at 12:51
 **/
public class UpdatePersonalInfo implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(PARAM_NAME_USER);
        int id = user.getId();
        Map<String, String> userInfo = getUserInfo(req);
        ServiceProvider provider = ServiceProvider.getInstance();
        UserServiceImpl service = provider.getUserService();
        UserValidator validator = service.getValidator();
        boolean result;
        try {
            result = service.update(id, userInfo);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        if (result) {
            user.setFirstName(userInfo.get(PARAM_NAME_FIRST_NAME));
            user.setLastName(userInfo.get(PARAM_NAME_LAST_NAME));
            user.setPhone(userInfo.get(PARAM_NAME_PHONE));
            user.setEmail(userInfo.get(PARAM_NAME_EMAIL));
            session.setAttribute(PARAM_NAME_USER, user);
        }
        String url = formNextUrl(result, validator, PARAM_NAME_OPERATION_RESULT, PARAM_VALUE_OPERATION_RESULT_SUCCESS,
                GO_TO_PERSONAL_INFO);
        resp.sendRedirect(url);
    }
}
