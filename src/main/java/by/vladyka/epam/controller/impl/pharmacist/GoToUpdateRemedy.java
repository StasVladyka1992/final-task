package by.vladyka.epam.controller.impl.pharmacist;

import by.vladyka.epam.controller.Command;
import by.vladyka.epam.entity.Remedy;
import by.vladyka.epam.service.RemedyService;
import by.vladyka.epam.service.ServiceProvider;
import by.vladyka.epam.service.exception.ServiceException;
import by.vladyka.epam.service.impl.RemedyServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.vladyka.epam.controller.util.JSPNavigation.UPDATE_REMEDY;
import static by.vladyka.epam.controller.util.ParameterName.ID;

/**
 * Created by Vladyka Stas
 * on 11.03.2019 at 10:58
 **/
public class GoToUpdateRemedy implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException, IOException, ServletException {
        int id = Integer.parseInt(req.getParameter(ID));
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        RemedyService service = serviceProvider.getRemedyService();
        Remedy remedy = service.findById(id);

        rememberLastPage(req);
        if (remedy == null) {
            req.getRequestDispatcher(UPDATE_REMEDY).forward(req, resp);
        }
        else {
            req.setAttribute("remedy", remedy);
            req.getRequestDispatcher(UPDATE_REMEDY).forward(req, resp);
        }


    }
}
