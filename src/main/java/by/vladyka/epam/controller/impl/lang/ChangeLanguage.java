package by.vladyka.epam.controller.impl.lang;

import by.vladyka.epam.controller.Command;
import by.vladyka.epam.controller.exception.UnsupportedMethodException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.vladyka.epam.controller.util.ParameterName.LANGUAGE;
import static by.vladyka.epam.controller.util.ParameterName.PREVIOUS_URL;

/**
 * Created by Vladyka Stas
 * on 16.02.2019 at 13:12
 **/
public class ChangeLanguage implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String language = req.getParameter(LANGUAGE);
        HttpSession session = req.getSession(true);
        session.setAttribute(LANGUAGE, language);
        String url = (String) session.getAttribute(PREVIOUS_URL);
        resp.sendRedirect(url);
    }

    @Override
    public void rememberLastPage(HttpServletRequest req) {
        //empty body
    }
}
