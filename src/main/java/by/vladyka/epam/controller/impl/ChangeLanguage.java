package by.vladyka.epam.controller.impl;

import by.vladyka.epam.controller.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Vladyka Stas
 * on 16.02.2019 at 13:12
 **/
public class ChangeLanguage implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String language = req.getParameter("language");
        HttpSession session = req.getSession(true);
        session.setAttribute("language", language);
        String url = (String) session.getAttribute("previous_url");
        resp.sendRedirect(url);
    }
}
