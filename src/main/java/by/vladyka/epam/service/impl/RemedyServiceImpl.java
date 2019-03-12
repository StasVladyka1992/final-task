package by.vladyka.epam.service.impl;

import by.vladyka.epam.dao.DAOProvider;
import by.vladyka.epam.dao.exception.DAOException;
import by.vladyka.epam.entity.Remedy;
import by.vladyka.epam.entity.RemedySearchingResult;
import by.vladyka.epam.service.RemedyService;
import by.vladyka.epam.service.exception.ServiceException;
import by.vladyka.epam.service.validator.impl.RemedyInfoValidator;

import java.util.List;

/**
 * Created by Vladyka Stas
 * on 26.02.2019 at 1:54
 **/
public class RemedyServiceImpl implements RemedyService {
    private RemedyInfoValidator remedyInfoValidator = new RemedyInfoValidator();

    @Override
    public Remedy findById(int id) throws ServiceException {
        boolean validationResult = remedyInfoValidator.checkIdAndSetMessage(id);
        Remedy remedy = null;
        if (validationResult){
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
    public List<Remedy> findAll() throws ServiceException {
        return null;
    }

    @Override
    public RemedySearchingResult findFromStartPosition(String name, int start, int offset) throws ServiceException {
        boolean validationResult = remedyInfoValidator.checkNameAndSetMessage(name);
        if (!validationResult) {
            return null;
        }
        RemedySearchingResult remedySearchingResult;
        DAOProvider daoProvider = DAOProvider.getInstance();
        try {
            remedySearchingResult = daoProvider.getSQLRemedyDAO().findFromStartPosition(name, start, offset);
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
        return remedySearchingResult;
    }

    @Override
    public boolean update(int id, String name, String description, double price, boolean receiptRequired) throws ServiceException {
        boolean validationResult = remedyInfoValidator.isRemedyAddingDataCorrect(name, description, price, receiptRequired);
        if(!validationResult){
            return false;
        }
        DAOProvider daoProvider = DAOProvider.getInstance();
        boolean isUpdatigSuccessfull;
        try {
            isUpdatigSuccessfull = daoProvider.getSQLRemedyDAO().update(id, name, description, price, receiptRequired);
        }
        catch (DAOException ex){
            throw new ServiceException(ex);
        }
        return isUpdatigSuccessfull;
    }

    @Override
    public boolean add(String name, String decscription, double price, boolean receiptRequired) throws ServiceException {
        boolean validationResult = remedyInfoValidator.isRemedyAddingDataCorrect(name, decscription, price,
                receiptRequired);
        if (!validationResult) {
            return false;
        }
        DAOProvider daoProvider = DAOProvider.getInstance();
        boolean isAddingSuccessfull;
        try {
            isAddingSuccessfull = daoProvider.getSQLRemedyDAO().create(name, decscription, price, receiptRequired);
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
        return isAddingSuccessfull;
    }

    @Override
    public boolean delete(int id) throws ServiceException {
        boolean isDeletingSuccessfull = false;
        if (remedyInfoValidator.checkIdAndSetMessage(id)) {
            DAOProvider daoProvider = DAOProvider.getInstance();
            try {
                isDeletingSuccessfull = daoProvider.getSQLRemedyDAO().deleteById(id);
            } catch (DAOException e) {
                throw new ServiceException(e);
            }
        }
        return isDeletingSuccessfull;
    }

    public RemedyInfoValidator getRemedyInfoValidator() {
        return remedyInfoValidator;
    }

    public void setRemedyInfoValidator(RemedyInfoValidator remedyInfoValidator) {
        this.remedyInfoValidator = remedyInfoValidator;
    }
}
