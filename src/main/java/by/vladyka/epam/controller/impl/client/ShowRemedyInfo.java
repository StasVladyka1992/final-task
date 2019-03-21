package by.vladyka.epam.controller.impl.client;

import by.vladyka.epam.controller.Command;
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
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException, IOException, ServletException {
        int id = Integer.parseInt(req.getParameter(PARAM_NAME_ID));
        ServiceProvider provider = ServiceProvider.getInstance();
        StorageServiceImpl service = provider.getStorageService();
        Storage storage = service.findById(id);
        HttpSession session = req.getSession();
        session.setAttribute(PARAM_NAME_STORAGE, storage);
        rememberLastPage(req);
        req.getRequestDispatcher(REMEDY_INFO).forward(req, resp);
    }
}
