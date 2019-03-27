package by.vladyka.epam.controller.impl.client;

import by.vladyka.epam.controller.Command;
import by.vladyka.epam.controller.exception.CommandException;
import by.vladyka.epam.entity.Storage;
import by.vladyka.epam.service.ServiceProvider;
import by.vladyka.epam.service.exception.ServiceException;
import by.vladyka.epam.service.impl.StorageServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.vladyka.epam.controller.util.JSPNavigation.REMEDY_INFO;
import static by.vladyka.epam.controller.util.ParameterName.PARAM_NAME_ID;
import static by.vladyka.epam.controller.util.ParameterName.PARAM_NAME_STORAGE;

/**
 * Created by Vladyka Stas
 * on 20.03.2019 at 1:32
 **/
public class ShowRemedyInfo implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException, IOException,
            ServletException {
        int id = Integer.parseInt(req.getParameter(PARAM_NAME_ID));
        StorageServiceImpl service = ServiceProvider.getInstance().getStorageService();
        Storage storage;
        try {
            storage = service.findById(id);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        HttpSession session = req.getSession();
        session.setAttribute(PARAM_NAME_STORAGE, storage);
        rememberLastRequest(req);
        req.getRequestDispatcher(REMEDY_INFO).forward(req, resp);
    }
}
