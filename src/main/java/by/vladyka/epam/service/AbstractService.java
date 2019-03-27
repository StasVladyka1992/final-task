package by.vladyka.epam.service;

import by.vladyka.epam.entity.AbstractEntity;
import by.vladyka.epam.service.exception.ServiceException;

/**
 * An interface that declare common activities with all entities in application
 *
 * @author Stas Vladyka
 * @version 1.0
 **/
public interface AbstractService<T extends AbstractEntity> {

    /**
     * Deletes entity from data source
     *
     * @param id deleting entity id
     * @return deleting result. {@code true} - deleting successful, false - failed
     * @throws ServiceException if there was exception during interaction with data source
     */
    boolean delete(int id) throws ServiceException;

    /**
     * Searches entity in data
     *
     * @param id searching entity id
     * @return searching entity
     * @throws ServiceException if there was exception during interaction with data source
     * @see AbstractEntity
     */
    T findById(int id) throws ServiceException;
}
