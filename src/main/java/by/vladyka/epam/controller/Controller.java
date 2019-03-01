package by.vladyka.epam.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class Controller extends HttpServlet {
//    TODO указать welcome page в web.xml
//    TODO указать страницу c ошбикой в web.xml
//    TODO сделать отсылку оплаченного счета стр. 163 Head First: Servlets and JSP
    //TODO сделать список лекарств на стартовой странице
    //TODO при обработке исключения в команде, перебрасывать на какую-либо страницу, а не выбрасывать ServletException
    //TODO сделать фильтр при поиске результатов в таблице
    //TODO сделать валидацию данных формы при помощи js
    //TODO закинуть имена параметров в константы
    //TODO переписать validator для remedy
    //TODO если используется doPost и doGet делают одно и то же, то лучше сделать 3 метод, который будет позволить не трогать
    //doPost и оставить его чистым для использования
    //TODO БД синхронизирует внесение записей сама. А вот доступ к Connection надо syncronize. Можно сделать коллекцию,
    // которая хранит коннекш concurrent
    //TODO Сделать rollback операции при покупке книги.При rollback ПОСЛЕ commit (или в connection pool) сбрасывать настройки Сonnection до default
    //TODO Сделать connection pool. см. лекцию.
    //


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String commandName = req.getParameter("command");
        CommandStorage commandStorage = new CommandStorage();
        commandStorage.getCommand(commandName).execute(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
