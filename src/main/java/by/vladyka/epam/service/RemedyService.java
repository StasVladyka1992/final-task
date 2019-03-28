package by.vladyka.epam.service;

import by.vladyka.epam.entity.Remedy;
import by.vladyka.epam.service.exception.ServiceException;

/**
 * An interface that declare activities with {@link Remedy}
 *
 * @author Stas Vladyka
 * @version 1.0
 **/
public interface RemedyService<T extends Remedy> {

    /**
     * Updates remedy data
     *
     * @param id              remedy id
     * @param name            remedy name
     * @param description     remedy description
     * @param price           remedy price
     * @param receiptRequired indicates is remedy requires prescriptions in
     *                        order to buy it
     * @return result of remedy updating, true - remedy updated, false otherwise
     * @throws ServiceException if there was exception during interaction with data source
     */
    boolean update(int id, String name, String description, double price, boolean receiptRequired)
            throws ServiceException;


    /**
     * Creates new remedy
     *
     * @param name            remedy name
     * @param description     remedy description
     * @param price           remedy price
     * @param receiptRequired indicates is remedy requires prescriptions in
     *                        order to buy it
     * @return result of remedy updating, true - remedy is updated, false otherwise
     * @throws ServiceException if there was exception during interaction with data source
     */
    boolean create(String name, String description, double price, boolean receiptRequired)
            throws ServiceException;

    /**
     * Finds remedy with specified id
     *
     * @param id remedy id
     * @return remedy with specified id
     * @throws ServiceException if there was exception during interaction with data source
     */
    T findById(int id) throws ServiceException;

    /**
     * Deletes remedy with specified id
     *
     * @param id remedy id
     * @return result of remedy deleting true - remedy is deleted, false otherwise
     * @throws ServiceException
     */
    boolean delete(int id) throws ServiceException;
}
