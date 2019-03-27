package by.vladyka.epam.service.impl;

import by.vladyka.epam.dao.DAOProvider;
import by.vladyka.epam.dao.StorageDAO;
import by.vladyka.epam.dao.exception.DAOException;
import by.vladyka.epam.dto.EntitySearchingResult;
import by.vladyka.epam.entity.Storage;
import by.vladyka.epam.service.StorageService;
import by.vladyka.epam.service.exception.ServiceException;
import by.vladyka.epam.service.validator.impl.StorageValidator;

/**
 * Created by Vladyka Stas
 * on 11.03.2019 at 14:00
 **/
public class StorageServiceImpl implements StorageService {
    private StorageValidator validator = new StorageValidator();

    @Override
    public boolean update(int remedyId, int remedyLeft) throws ServiceException {
        if (!validator.checkAddingDataAndSetMessage(remedyId, remedyLeft)) {
            return false;
        }
        DAOProvider daoProvider = DAOProvider.getInstance();
        boolean isAddingSuccessful;
        try {
            isAddingSuccessful = daoProvider.getSQLStorageDAO().update(remedyId, remedyLeft);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return isAddingSuccessful;
    }

    @Override
    public boolean create(int remedyId, int remedyLeft) throws ServiceException {
        if (!validator.checkAddingDataAndSetMessage(remedyId, remedyLeft)) {
            return false;
        }
        DAOProvider daoProvider = DAOProvider.getInstance();
        boolean isAddingSuccessful;
        try {
            isAddingSuccessful = daoProvider.getSQLStorageDAO().create(remedyId, remedyLeft);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return isAddingSuccessful;
    }

    @Override
    public boolean delete(int id) throws ServiceException {
        return false;
    }

    @Override
    public Storage findById(int id) throws ServiceException {
        boolean validationResult;
        validationResult = validator.checkId(id);
        Storage storage;
        if (!validationResult) {
            return null;
        } else {
            StorageDAO dao = DAOProvider.getInstance().getSQLStorageDAO();
            try {
                storage = dao.findById(id);
            } catch (DAOException e) {
                throw new ServiceException(e);
            }
        }
        return storage;
    }

    @Override
    public EntitySearchingResult<Storage> findFromStartPosition(String name, int start, int offset) throws
            ServiceException {
        boolean validationResult = validator.checkNameAndSetMessage(name);
        if (!validationResult) {
            return null;
        }
        EntitySearchingResult entitySearchingResult;
        DAOProvider daoProvider = DAOProvider.getInstance();
        try {
            entitySearchingResult = daoProvider.getSQLStorageDAO().findFromStartPosition(name, start, offset);
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
        return entitySearchingResult;
    }

    public StorageValidator getValidator() {
        return validator;
    }

    public void setValidator(StorageValidator validator) {
        this.validator = validator;
    }
}
