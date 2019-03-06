package by.vladyka.epam.service.impl;

import by.vladyka.epam.dao.DAOProvider;
import by.vladyka.epam.dao.UserDAO;
import by.vladyka.epam.dao.exception.DAOException;
import by.vladyka.epam.entity.User;
import by.vladyka.epam.service.UserService;
import by.vladyka.epam.service.exception.ServiceException;
import by.vladyka.epam.service.validator.impl.CredentialsValidator;

import java.util.Map;

import static by.vladyka.epam.service.validator.util.IncorrectDataMessage.USER_EXIST;


/**
 * Created by Vladyka Stas
 * on 17.02.2019 at 14:46
 **/
public class UserServiceImpl implements UserService {
    private CredentialsValidator credentialsValidator;
    private StringBuilder incorrectDataMessages;

    //Почему пишет ошибку, когда я пишу Map <String, String> userData?
    @Override
    public boolean registration(Map userData) throws ServiceException {
        //TODO может происходить одновременная регистрация 2 разных пользователей с одной и той же почтой
        credentialsValidator = new CredentialsValidator();
        boolean isRegistrationDataCorrect = credentialsValidator.isRegistrationDataCorrect(userData);
        if (!isRegistrationDataCorrect) {
            incorrectDataMessages = credentialsValidator.getIncorrectMessages();
            return false;
        }
        DAOProvider daoProvider = DAOProvider.getInstance();
        boolean isRegistrationPerfomed;
        try {
            isRegistrationPerfomed = daoProvider.getSQLUserDAO().registration(userData);
            if (!isRegistrationPerfomed) {
                incorrectDataMessages = new StringBuilder();
                incorrectDataMessages.append(USER_EXIST);
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return isRegistrationPerfomed;
    }

    @Override
    public User authorization(String email, String password) throws ServiceException {
        credentialsValidator = new CredentialsValidator();
        if (!credentialsValidator.isAuthorizationDataCorrect(email, password)) {
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

    public String getIncorrectDataMessages() {
        return incorrectDataMessages.toString();
    }

}
