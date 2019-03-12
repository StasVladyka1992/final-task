package by.vladyka.epam.service.impl;

import by.vladyka.epam.dao.DAOProvider;
import by.vladyka.epam.dao.RemedyDAO;
import by.vladyka.epam.dao.StorageDAO;
import by.vladyka.epam.dao.exception.DAOException;
import by.vladyka.epam.dao.impl.SQLRemedyDAO;
import by.vladyka.epam.entity.Storage;
import by.vladyka.epam.service.StorageService;
import by.vladyka.epam.service.exception.ServiceException;
import by.vladyka.epam.service.validator.impl.StoragePositionValidator;

import java.util.List;

/**
 * Created by Vladyka Stas
 * on 11.03.2019 at 14:00
 **/
public class StorageServiceImpl implements StorageService {
    private StoragePositionValidator validator = new StoragePositionValidator();
    @Override
    public boolean update(int id, int remedyId, int remedyLeft) throws ServiceException {
        return false;
    }

    @Override
    public boolean add(int remedyId, int remedyLeft) throws ServiceException {
        return false;
    }

    @Override
    public boolean delete(int id) throws ServiceException {
        return false;
    }

    @Override
    public Storage findById(int id) throws ServiceException {
        boolean validationResult;
        validationResult = validator.checkIdAndSetMessage(id);
        Storage storage;
        if(!validationResult){
            return null;
        }else {
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
    public List<Storage> findAll() throws ServiceException {
        return null;
    }
}
