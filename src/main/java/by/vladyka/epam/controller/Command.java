package by.vladyka.epam.controller;

import by.vladyka.epam.controller.util.URLRestorer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public interface Command {
    void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
    default void rememberLastPage (HttpServletRequest req){
        HttpSession session = req.getSession(true);
        String url = URLRestorer.restoreURL(req);
        session.setAttribute("previous_url", url);
    }
}
