package by.vladyka.epam.service.impl;

import by.vladyka.epam.dao.DAOProvider;
import by.vladyka.epam.dao.UserDAO;
import by.vladyka.epam.dao.exception.DAOException;
import by.vladyka.epam.entity.User;
import by.vladyka.epam.service.UserService;
import by.vladyka.epam.service.exception.ServiceException;
import by.vladyka.epam.service.validator.impl.UserValidator;

import java.util.Map;

import static by.vladyka.epam.controller.util.ParameterName.*;
import static by.vladyka.epam.service.validator.util.IncorrectDataMessage.USER_EXIST;


/**
 * Created by Vladyka Stas
 * on 17.02.2019 at 14:46
 **/
public class UserServiceImpl implements UserService {
    private UserValidator validator = new UserValidator();

    @Override
    public boolean registration(Map userInfo, String password, User.UserRole role)
            throws ServiceException {
        validator.cleanBuffer();
        boolean isRegistrationDataCorrect = validator.checkRegistrationDataAndSetMessage(userInfo, role, password);
        if (!isRegistrationDataCorrect) {
            return false;
        }
        DAOProvider daoProvider = DAOProvider.getInstance();
        boolean isRegistrationPerformed;
        try {
            isRegistrationPerformed = daoProvider.getSQLUserDAO().registration((String) userInfo.get(PARAM_NAME_EMAIL),
                    (String) userInfo.get(PARAM_NAME_FIRST_NAME), (String) userInfo.get(PARAM_NAME_LAST_NAME),
                    (String) userInfo.get(PARAM_NAME_PHONE), password, role);
            if (!isRegistrationPerformed) {
                validator.addIncorrectDataMessage(USER_EXIST);
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return isRegistrationPerformed;
    }

    @Override
    public User authorization(String email, String password) throws ServiceException {
        validator.cleanBuffer();
        if (!validator.checkAuthorizationDataAndSetMessage(email, password)) {
            return null;
        }
        DAOProvider daoProvider = DAOProvider.getInstance();
        UserDAO userDAO = daoProvider.getSQLUserDAO();
        User user;
        try {
            user = userDAO.authentication(email, password);
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
        return user;
    }

    public boolean update(int id, Map userInfo) throws ServiceException {
        validator.cleanBuffer();
        boolean isRegistrationDataCorrect = validator.checkUpdateDataAndSetMessage(userInfo);
        if (!isRegistrationDataCorrect) {
            return false;
        }
        DAOProvider daoProvider = DAOProvider.getInstance();
        boolean isUpdatePerformed;
        try {
            isUpdatePerformed = daoProvider.getSQLUserDAO().update(id, (String) userInfo.get(PARAM_NAME_EMAIL),
                    (String) userInfo.get(PARAM_NAME_FIRST_NAME), (String) userInfo.get(PARAM_NAME_LAST_NAME),
                    (String) userInfo.get(PARAM_NAME_PHONE));
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return isUpdatePerformed;
    }

    public UserValidator getValidator() {
        return validator;
    }

    public void setValidator(UserValidator validator) {
        this.validator = validator;
    }
}
