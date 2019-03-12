package by.vladyka.epam.controller;

import by.vladyka.epam.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class Controller extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(Controller.class);
    private final CommandStorage commandStorage = new CommandStorage();
    private static final String COMMAND_PARAMETER = "command";

    //TODO указать welcome page в web.xml
    //TODO сделать отсылку оплаченного счета стр. 163 Head First: Servlets and JSP
    //TODO сделать список лекарств на стартовой странице
    //TODO сделать валидацию add_remedy, registration, authorization и в остальных вводах при помощи js, чтоб сохран. введен. инфа
    //TODO Сделать rollback операции при покупке книги.При rollback ПОСЛЕ commit (или в connection pool) сбрасывать настройки Сonnection до default
    //TODO просмотреть классы, чтобы не исполнялись пустые команды, если в них не будет потребности в следующих строчках кода
    //TODO если ParameterDataExtractor будет исполняться один раз, то подумать над тем, чтобы сделать его аннонимным
    //TODO сделать так, чтобы метод findFromStartPosition работал на критериях, как в дз
    //TODO в случае возникновения exception должно каждый раз выводит разное сообщение, т.е. на каждую ошибку - свое сообщение.
    //TODO Пагинацию сделать через commit
    //TODO Listener сделан через аннотацию код для замены в web.xml
    //TODO при создании User в UserRegistration было бы проще сразу же вернуть готовый объект, а не заниматься созданием в Сontroller, то же самое относится и к добавлению Remedy
    //TODO придумать, как я могу не создавать в каждом классе один и тот же logger
    //TODO FindRemedy - only one command for all users. So it's need to know, which remedy page show to user
    //TODO вместо целой команды - создать просто метод внутри класса userNavigationManager\
    //TODO вытащить из entity RemedySearchingResult и сделать его внутренним классом, или просто создать HashMap
    //TODO атрибуты для incorrectmessage можно сэтать в req attribute;
    //!!!ВАЖНО
    //TODO попросить посмотреть реализацию SQLRemedyDAO
    //TODO не видит других Remedy, а показывает только первое значениу



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
        }
        catch (ServiceException ex){
            //TODO логгирование
        }
    }
}
