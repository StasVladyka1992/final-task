package by.vladyka.epam.controller;

import by.vladyka.epam.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.vladyka.epam.controller.util.JSPNavigation.ERROR_PAGE;

//TODO указать welcome page в web.xml
//TODO сделать отсылку оплаченного счета стр. 163 Head First: Servlets and JSP
//TODO сделать список лекарств на стартовой странице
//TODO просмотреть классы, чтобы не исполнялись пустые команды, если в них не будет потребности в следующих строчках кода
//TODO Пагинацию сделать через commit
//TODO Listener сделан через аннотацию код для замены в web.xml
//TODO КОГДА ОПЕРАЦИЯ ОБНОВЛЕНИЯ В БД ВОЗВРАЩАЕТ false, ДОБАВЛЯТЬ в incorrectDataMessage СООБЩЕНИЕ О ПРИЧИНЕ ТАКОЙ ОПЕРАЦИИ
//TODO Выравнить блоки по высоте на главных страница доктора, клиента и пользователя
//TODO если рецепт истек  - не продлевать его, а выписывать новый.
//!!!ВАЖНО
//TODO удалять из сессии списки объектов после прехода на другие страницы. Сделать это с jsp страницы.
//TODO создать Builder для создания объектов внутри DAO
//TODO почему валится команда "Проверить список заявок", которая отправляется методом get на странице prescription
//TODO com.mysql.jdbc.JDBC42PreparedStatement@76920921: EXCEPTION: java.sql.SQLException: No operations allowed after statement closed.
//TODO 18-Mar-2019 20:12:41.709 WARNING [RMI TCP Connection(15)-127.0.0.1] org.apache.catalina.loader.WebappClassLoaderBase.clearReferencesJdbc The web application [ROOT] registered the JDBC driver [com.mysql.jdbc.Driver] but failed to unregister it when the web application was stopped. To prevent a memory leak, the JDBC Driver has been forcibly unregistered.
//18-Mar-2019 20:12:41.709 WARNING [RMI TCP Connection(15)-127.0.0.1] org.apache.catalina.loader.WebappClassLoaderBase.clearReferencesThreads The web application [ROOT] is still processing a request that has yet to finish. This is very likely to create a memory leak. You can control the time allowed for requests to finish by using the unloadDelay attribute of the standard Context implementation. Stack trace of request processing thread:[
// sun.misc.Unsafe.park(Native Method)

//18-Mar-2019 20:12:41.729 WARNING [RMI TCP Connection(15)-127.0.0.1] org.apache.catalina.loader.WebappClassLoaderBase.clearReferencesThreads The web application [ROOT] is still processing a request that has yet to finish. This is very likely to create a memory leak. You can control the time allowed for requests to finish by using the unloadDelay attribute of the standard Context implementation. Stack trace of request processing thread:[
//        sun.misc.Unsafe.park(Native Method)
//TODO на jsp сделать switch вместо кучи if
//TODO при повторном обращение за заявкой она появляется в url + пишет, что заявка уже существует. Разобраться!!!
//TODO заюзать HashMap в UserRegistration
//TODO вывести список отказанных заявок на рецепты у клиента

public class Controller extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(Controller.class);
    private final CommandStorage commandStorage = new CommandStorage();
    private static final String COMMAND_PARAMETER = "command";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String commandName = req.getParameter(COMMAND_PARAMETER);
        try {
            commandStorage.getCommand(commandName).execute(req, resp);
        } catch (ServiceException ex) {
            logger.error(ex);
//            resp.sendRedirect(ERROR_PAGE);
        }
    }
}
