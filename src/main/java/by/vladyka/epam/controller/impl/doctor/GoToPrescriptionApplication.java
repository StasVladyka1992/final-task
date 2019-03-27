package by.vladyka.epam.controller.impl.doctor;

import by.vladyka.epam.controller.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.vladyka.epam.controller.util.JSPNavigation.PRESCRIPTION_APPLICATION;

/**
 * Created by Vladyka Stas
 * on 15.03.2019 at 15:52
 **/
public class GoToPrescriptionApplication implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        rememberLastRequest(req);
        req.getRequestDispatcher(PRESCRIPTION_APPLICATION).forward(req, resp);
    }
}
