package by.vladyka.epam.service.impl;

import by.vladyka.epam.dao.DAOProvider;
import by.vladyka.epam.dao.exception.DAOException;
import by.vladyka.epam.entity.RemedySearchingResult;
import by.vladyka.epam.service.RemedyService;
import by.vladyka.epam.service.exception.ServiceException;
import by.vladyka.epam.service.validator.impl.RemedyInfoValidator;

import java.util.Map;

/**
 * Created by Vladyka Stas
 * on 26.02.2019 at 1:54
 **/
public class RemedyServiceImpl implements RemedyService {
    private RemedyInfoValidator remedyInfoValidator = new RemedyInfoValidator();
    private StringBuilder incorrectDataMessages;

    @Override
    public RemedySearchingResult find(String name, int start, int offset) throws ServiceException {
        boolean validationResult = remedyInfoValidator.isSearchingParametersCorrect(name);
        if (!validationResult) {
            incorrectDataMessages = remedyInfoValidator.getIncorrectMessages();
            return null;
        }
        RemedySearchingResult remedySearchingResult;
        DAOProvider daoProvider = DAOProvider.getInstance();
        try {
            remedySearchingResult = daoProvider.getSQLRemedyDAO().findRemedy(name, start, offset);
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
        return remedySearchingResult;
    }

    @Override
    public boolean update(Map<String, String> parameters) {
        return false;
    }

    @Override
    public boolean add(Map<String, String> parameters) throws ServiceException {
        boolean validationResult = remedyInfoValidator.isRemedyAddingDataCorrect(parameters);
        if (!validationResult) {
            incorrectDataMessages = remedyInfoValidator.getIncorrectMessages();
            return false;
        }
        DAOProvider daoProvider = DAOProvider.getInstance();
        boolean isAddingSuccessfull;
        try {
            isAddingSuccessfull = daoProvider.getSQLRemedyDAO().addRemedy(parameters);
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
        return isAddingSuccessfull;
    }

    @Override
    public boolean delete(Map<String, String> parameters) {
        return false;
    }

    public String getIncorrectDataMessages() {
        return incorrectDataMessages.toString();
    }
}
