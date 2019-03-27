package by.vladyka.epam.controller.impl.lang;

import by.vladyka.epam.controller.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.vladyka.epam.controller.util.ParameterName.PARAM_NAME_LANGUAGE;
import static by.vladyka.epam.controller.util.ParameterName.PARAM_NAME_PREVIOUS_URL;

/**
 * Created by Vladyka Stas
 * on 16.02.2019 at 13:12
 **/
public class ChangeLanguage implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String language = req.getParameter(PARAM_NAME_LANGUAGE);
        HttpSession session = req.getSession();
        session.setAttribute(PARAM_NAME_LANGUAGE, language);
        String url = (String) session.getAttribute(PARAM_NAME_PREVIOUS_URL);
        resp.sendRedirect(url);
    }
}
