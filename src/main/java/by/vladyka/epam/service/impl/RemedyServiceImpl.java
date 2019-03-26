package by.vladyka.epam.service.impl;

import by.vladyka.epam.dao.DAOProvider;
import by.vladyka.epam.dao.exception.DAOException;
import by.vladyka.epam.entity.Remedy;
import by.vladyka.epam.service.RemedyService;
import by.vladyka.epam.service.exception.ServiceException;
import by.vladyka.epam.service.validator.impl.RemedyValidator;

import java.util.List;

/**
 * Created by Vladyka Stas
 * on 26.02.2019 at 1:54
 **/
public class RemedyServiceImpl implements RemedyService {
    private RemedyValidator validator = new RemedyValidator();

    @Override
    public Remedy findById(int id) throws ServiceException {
        boolean validationResult = validator.checkId(id);
        Remedy remedy = null;
        if (validationResult) {
            DAOProvider provider = DAOProvider.getInstance();
            try {
                remedy = provider.getSQLRemedyDAO().findById(id);
            } catch (DAOException e) {
                throw new ServiceException(e);
            }
        }
        return remedy;
    }



    @Override
    public boolean update(int id, String name, String description, double price, boolean receiptRequired) throws ServiceException {
        boolean validationResult = validateCreateData(name, description, price, receiptRequired) &&
                validator.checkId(id);
        if (!validationResult) {
            return false;
        }
        DAOProvider daoProvider = DAOProvider.getInstance();
        boolean isUpdatigSuccessfull;
        try {
            isUpdatigSuccessfull = daoProvider.getSQLRemedyDAO().update(id, name, description, price, receiptRequired);
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
        return isUpdatigSuccessfull;
    }

    @Override
    public boolean create(String name, String description, double price, boolean receiptRequired) throws ServiceException {
        if (!validateCreateData(name, description, price, receiptRequired)) {
            return false;
        }
        DAOProvider daoProvider = DAOProvider.getInstance();
        boolean isAddingSuccessfull;
        try {
            isAddingSuccessfull = daoProvider.getSQLRemedyDAO().create(name, description, price, receiptRequired);
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
        return isAddingSuccessfull;
    }

    @Override
    public boolean delete(int id) throws ServiceException {
        boolean isDeletingSuccessfull = false;
        if (validator.checkId(id)) {
            DAOProvider daoProvider = DAOProvider.getInstance();
            try {
                isDeletingSuccessfull = daoProvider.getSQLRemedyDAO().deleteById(id);
            } catch (DAOException e) {
                throw new ServiceException(e);
            }
        }
        return isDeletingSuccessfull;
    }

    private boolean validateCreateData(String name, String description, double price, boolean receiptRequired) {
        boolean validationResult = validator.checkRemedyAddingDataAndSetMessage(name, description, price, receiptRequired);
        if (!validationResult) {
            return false;
        }
        return true;
    }

    public RemedyValidator getValidator() {
        return validator;
    }

    public void setValidator(RemedyValidator validator) {
        this.validator = validator;
    }
}
