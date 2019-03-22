package by.vladyka.epam.controller.impl.pharmacist;

import by.vladyka.epam.controller.Command;
import by.vladyka.epam.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.vladyka.epam.controller.util.JSPNavigation.PURCHASE_ADMINISTRATION;

/**
 * Created by Vladyka Stas
 * on 21.03.2019 at 23:16
 **/
public class GoToPurchaseAdministration implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException, IOException, ServletException {
        rememberLastRequest(req);
        req.getRequestDispatcher(PURCHASE_ADMINISTRATION).forward(req,resp);
    }
}
