package by.vladyka.epam.controller.impl.pharmacist;

import by.vladyka.epam.controller.Command;
import by.vladyka.epam.entity.Storage;
import by.vladyka.epam.service.ServiceProvider;
import by.vladyka.epam.service.StorageService;
import by.vladyka.epam.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.vladyka.epam.controller.util.JSPNavigation.UPDATE_REMEDY;
import static by.vladyka.epam.controller.util.ParameterName.ID;

/**
 * Created by Vladyka Stas
 * on 11.03.2019 at 18:04
 **/
public class GoToUpdateRemedyQuantity implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException, IOException, ServletException {
        int id = Integer.parseInt(req.getParameter(ID));
        ServiceProvider provider = ServiceProvider.getInstance();
        StorageService service = provider.getStorageService();
        Storage storagePosition = service.findById(id);
        rememberLastPage(req);
        if (storagePosition == null) {
            req.getRequestDispatcher(UPDATE_REMEDY).forward(req, resp);
        }
        else {
            req.setAttribute("storagePosition", storagePosition);
            req.getRequestDispatcher(UPDATE_REMEDY).forward(req, resp);
        }

    }
}
