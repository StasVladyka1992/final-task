package by.vladyka.epam.service;

import by.vladyka.epam.dto.EntitySearchingResult;
import by.vladyka.epam.entity.Storage;
import by.vladyka.epam.service.exception.ServiceException;

/**
 * An interface that declare activities with {@link Storage}
 *
 * @author Stas Vladyka
 * @version 1.0
 **/
public interface StorageService extends AbstractService<Storage> {
    /**
     * Updates remedy quantity on storage if it has already been there
     *
     * @param remedyId   remedy id
     * @param remedyLeft new quantity of remedy on the storage
     * @return result of remedy updating, true - remedy is updated, false otherwise
     * @throws ServiceException if there was exception during interaction with data source
     */
    boolean update(int remedyId, int remedyLeft) throws ServiceException;

    /**
     * Adds remedy to the storage by setting it's quantity to 0 or higher
     *
     * @param remedyId   remedy id
     * @param remedyLeft new quantity of remedy on the storage
     * @return result of remedy creating, true - remedy is created, false otherwise
     * @throws ServiceException if there was exception during interaction with data source
     */
    boolean create(int remedyId, int remedyLeft) throws ServiceException;

    /**
     * Finds storage positions by name pattern
     *
     * @param name   remedy name
     * @param start  pagination parameter that indicates start position from
     *               which searching of storage positions will start
     * @param offset pagination parameter that indicates number of storage positions
     *               per page
     * @return storage positions and number of pages to show them.
     * @throws ServiceException if there was exception  during interaction with data source
     * @see EntitySearchingResult
     */
    EntitySearchingResult<Storage> findFromStartPosition(String name, int start, int offset) throws ServiceException;
}
