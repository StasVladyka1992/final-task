package by.vladyka.epam.service;

import by.vladyka.epam.entity.User;
import by.vladyka.epam.service.exception.ServiceException;

import java.util.Map;

/**
 * Created by Vladyka Stas
 * on 17.02.2019 at 14:44
 **/
public interface UserService<T extends User> {
    boolean registration(Map<String, String> userInfo, String password, User.UserRole role) throws ServiceException;

    T authorization(String email, String password) throws ServiceException;

    boolean update(int id, Map<String, String> userInfo)
            throws ServiceException;
}
