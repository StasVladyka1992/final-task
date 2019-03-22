package by.vladyka.epam.controller.impl.client;

import by.vladyka.epam.controller.Command;
import by.vladyka.epam.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Vladyka Stas
 * on 21.03.2019 at 23:42
 **/
public class ShowPrescription implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException, IOException, ServletException {

    }
}
