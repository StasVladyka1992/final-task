package by.vladyka.epam.service;

import by.vladyka.epam.entity.Storage;
import by.vladyka.epam.service.exception.ServiceException;

/**
 * Created by Vladyka Stas
 * on 11.03.2019 at 13:52
 **/
public interface StorageService extends AbstractService<Storage> {
    boolean update(int id, int remedyId, int remedyLeft) throws ServiceException;
    boolean add(int remedyId, int remedyLeft) throws ServiceException;

}