package by.vladyka.epam.service.impl;

import by.vladyka.epam.dao.DAOProvider;
import by.vladyka.epam.dao.exception.DAOException;
import by.vladyka.epam.entity.Remedy;
import by.vladyka.epam.service.RemedyService;
import by.vladyka.epam.service.exception.ServiceException;
import by.vladyka.epam.service.validator.impl.RemedyValidator;

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
    public boolean update(int id, String name, String description, double price, boolean receiptRequired)
            throws ServiceException {
        boolean validationResult = validator.checkRemedyAddingDataAndSetMessage(name, description, price,
                receiptRequired) && validator.checkId(id);
        if (!validationResult) {
            return false;
        }
        DAOProvider daoProvider = DAOProvider.getInstance();
        boolean isUpdatingSuccessful;
        try {
            isUpdatingSuccessful = daoProvider.getSQLRemedyDAO().update(id, name, description, price,
                    receiptRequired);
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
        return isUpdatingSuccessful;
    }

    @Override
    public boolean create(String name, String description, double price, boolean receiptRequired)
            throws ServiceException {
        if (!validator.checkRemedyAddingDataAndSetMessage(name, description, price, receiptRequired)) {
            return false;
        }
        DAOProvider daoProvider = DAOProvider.getInstance();
        boolean isAddingSuccessful;
        try {
            isAddingSuccessful = daoProvider.getSQLRemedyDAO().create(name, description, price, receiptRequired);
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
        return isAddingSuccessful;
    }

    @Override
    public boolean delete(int id) throws ServiceException {
        boolean isDeletingSuccessful = false;
        if (validator.checkId(id)) {
            DAOProvider daoProvider = DAOProvider.getInstance();
            try {
                isDeletingSuccessful = daoProvider.getSQLRemedyDAO().deleteById(id);
            } catch (DAOException e) {
                throw new ServiceException(e);
            }
        }
        return isDeletingSuccessful;
    }

    public RemedyValidator getValidator() {
        return validator;
    }

    public void setValidator(RemedyValidator validator) {
        this.validator = validator;
    }
}
