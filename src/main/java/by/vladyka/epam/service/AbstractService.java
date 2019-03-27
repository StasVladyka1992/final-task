package by.vladyka.epam.service;

import by.vladyka.epam.entity.AbstractEntity;
import by.vladyka.epam.service.exception.ServiceException;

/**
 * Created by Vladyka Stas
 * on 10.03.2019 at 14:23
 **/
public interface AbstractService<T extends AbstractEntity> {
    boolean delete(int id) throws ServiceException;

    T findById(int id) throws ServiceException;
}
