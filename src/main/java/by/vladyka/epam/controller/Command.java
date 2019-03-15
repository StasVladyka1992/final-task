package by.vladyka.epam.controller;

import by.vladyka.epam.controller.util.URLRestorer;
import by.vladyka.epam.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.vladyka.epam.controller.util.ParameterName.PARAM_NAME_PREVIOUS_URL;

public interface Command {
    void execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException, IOException, ServletException;
    default void rememberLastPage (HttpServletRequest req) {
        HttpSession session = req.getSession(true);
        String url = URLRestorer.restoreURL(req);
        session.setAttribute(PARAM_NAME_PREVIOUS_URL, url);
    }
}
