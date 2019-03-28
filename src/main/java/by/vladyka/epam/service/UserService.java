package by.vladyka.epam.service;

import by.vladyka.epam.entity.User;
import by.vladyka.epam.service.exception.ServiceException;

import java.util.Map;

/**
 * An interface that declare activities with {@link User}
 *
 * @author Stas Vladyka
 * @version 1.0
 **/
public interface UserService<T extends User> {
    /**
     * Creates new user
     *
     * @param userInfo see {@link by.vladyka.epam.controller.util.ParameterValue}
     * @param password account password
     * @param role     user role
     * @return result of user registration, true - registration is successful, false otherwise
     * @throws ServiceException if there was exception during interaction with data source
     * @see Map
     * @see User.UserRole
     */
    boolean registration(Map<String, String> userInfo, String password, T.UserRole role) throws ServiceException;

    /**
     * User authorization
     *
     * @param email user email
     * @param password user password
     * @return created user which will storage in session
     * @throws ServiceException if there was exception during interaction with data source
     */
    T authorization(String email, String password) throws ServiceException;

    boolean update(int id, Map<String, String> userInfo)
            throws ServiceException;
}
