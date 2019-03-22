package by.vladyka.epam.service;

import by.vladyka.epam.entity.User;
import by.vladyka.epam.service.exception.ServiceException;

/**
 * Created by Vladyka Stas
 * on 17.02.2019 at 14:44
 **/
public interface UserService<T extends User> {
    boolean registration(String email, String firstName, String lastName, String password, String phone,
                         User.UserRole role) throws ServiceException;

    T authorization(String email, String password) throws ServiceException;

    boolean update (int id, String email, String firstName, String lastName, String phone)
            throws ServiceException;
}
