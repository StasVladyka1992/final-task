package by.vladyka.epam.service.impl;

import by.vladyka.epam.dao.DAOCreator;
import by.vladyka.epam.dao.exception.DAOException;
import by.vladyka.epam.entity.Remedy;
import by.vladyka.epam.entity.RemedySearchingResult;
import by.vladyka.epam.service.RemedyService;
import by.vladyka.epam.service.exception.ServiceException;
import by.vladyka.epam.service.validator.impl.RemedyInfoValidator;

import java.util.List;
import java.util.Map;

/**
 * Created by Vladyka Stas
 * on 26.02.2019 at 1:54
 **/
public class RemedyServiceImpl implements RemedyService {
    private RemedyInfoValidator remedyInfoValidator;
    private StringBuilder incorrectDataMessages;
    @Override
    public RemedySearchingResult find(String name, int start, int offset) throws ServiceException {
        remedyInfoValidator = new RemedyInfoValidator();
        boolean validationResult = remedyInfoValidator.searchingParametersValidator(name);
        if (!validationResult) {
            incorrectDataMessages = remedyInfoValidator.getIncorrectMessages();
            return null;
        }
        RemedySearchingResult remedySearchingResult;
        DAOCreator daoCreator = DAOCreator.getInstance();
        try {
            remedySearchingResult = daoCreator.getSQLRemedyDAO().findRemedy(name, start, offset);
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
    public boolean add(Map<String, String> parameters) {
        return false;
    }

    @Override
    public boolean delete(Map<String, String> parameters) {
        return false;
    }

    public String getIncorrectDataMessages() {
        return incorrectDataMessages.toString();
    }
}
