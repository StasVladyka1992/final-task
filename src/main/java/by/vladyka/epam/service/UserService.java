package by.vladyka.epam.service;

import by.vladyka.epam.entity.User;
import by.vladyka.epam.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by Vladyka Stas
 * on 17.02.2019 at 14:44
 **/
public interface UserService<T extends User> {
    boolean registration(Map<String, String> userData) throws ServiceException;
    T authorization(String email, String password) throws ServiceException;
}
