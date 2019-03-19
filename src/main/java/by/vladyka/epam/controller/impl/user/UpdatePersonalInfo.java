package by.vladyka.epam.controller.impl.user;

import by.vladyka.epam.controller.Command;
import by.vladyka.epam.entity.User;
import by.vladyka.epam.service.ServiceProvider;
import by.vladyka.epam.service.UserService;
import by.vladyka.epam.service.exception.ServiceException;
import by.vladyka.epam.service.impl.RemedyServiceImpl;
import by.vladyka.epam.service.impl.UserServiceImpl;
import by.vladyka.epam.service.validator.impl.RemedyValidator;
import by.vladyka.epam.service.validator.impl.UserValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.vladyka.epam.controller.util.JSPNavigation.*;
import static by.vladyka.epam.controller.util.ParameterName.*;
import static by.vladyka.epam.controller.util.ParameterName.PARAM_NAME_OPERATION_RESULT;
import static by.vladyka.epam.controller.util.ParameterValue.PARAM_VALUE_OPERATION_RESULT_SUCCESS;

/**
 * Created by Vladyka Stas
 * on 19.03.2019 at 12:51
 **/
public class UpdatePersonalInfo implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException, IOException, ServletException {
        //TODO не сделан функционал по обновлению и изменению пароля. Изменение пароля отдельной командой, т.к. он не хранится
        //в сессии
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute(PARAM_NAME_USER);
        int id = user.getId();
        String firstName = req.getParameter(PARAM_NAME_FIRST_NAME);
        String lastName = req.getParameter(PARAM_NAME_LAST_NAME);
        String email = req.getParameter(PARAM_NAME_EMAIL);
        String phone = req.getParameter(PARAM_NAME_PHONE);

        ServiceProvider provider = ServiceProvider.getInstance();
        UserServiceImpl service = provider.getUserService();
        boolean result = service.update(email, firstName, lastName, phone);

        UserValidator validator = service.getValidator();
        String url = formNextUrl(result, validator, PARAM_NAME_OPERATION_RESULT, PARAM_VALUE_OPERATION_RESULT_SUCCESS,
                GO_TO_PERSONAL_INFO);
        resp.sendRedirect(url);
    }
}
