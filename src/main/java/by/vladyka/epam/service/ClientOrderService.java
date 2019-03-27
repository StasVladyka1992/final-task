package by.vladyka.epam.service;

import by.vladyka.epam.dto.EntitySearchingResult;
import by.vladyka.epam.dto.OrderDto;
import by.vladyka.epam.dto.OrderDtoForPharmacist;
import by.vladyka.epam.entity.ClientOrder;
import by.vladyka.epam.service.exception.ServiceException;

/**
 * An interface that declare activities with {@link ClientOrder}
 *
 * @author Stas Vladyka
 * @version 1.0
 **/
public interface ClientOrderService extends AbstractService<ClientOrder> {

    /**
     * Creates client order that need to be process by pharmacist
     *
     * @param clientId client id
     * @param orderDto an object that contains data about client order.
     * @return id of created {@link ClientOrder}
     * @throws ServiceException if there was exception during interaction with data source
     * @see OrderDto
     */
    int buy(int clientId, OrderDto orderDto) throws ServiceException;

    /**
     * Finds all unhandled orders for pharmacist
     *
     * @param start  pagination parameter that indicates start position from
     *               which searching of client orders will start
     * @param offset pagination parameter that indicates number of client orders
     *               per page
     * @return unhandled client orders and number of pages to show them
     * @throws ServiceException if there was exception  during interaction with data source
     * @see ClientOrder
     * @see EntitySearchingResult
     */
    EntitySearchingResult<ClientOrder> findUnhandledOrders(int start, int offset) throws ServiceException;

    /**
     * Finds all handled orders for pharmacist
     *
     * @param start  pagination parameter that indicates start position from
     *               which searching of client orders will start
     * @param offset pagination parameter that indicates number of client orders
     *               per page
     * @return handled client orders and number of pages to show them
     * @throws ServiceException if there was exception during interaction with data source
     * @see ClientOrder
     * @see EntitySearchingResult
     */
    EntitySearchingResult<ClientOrder> findHandledOrders(int start, int offset) throws ServiceException;

    /**
     * Confirms client order
     *
     * @param order an object that contains all important data about client order.
     * @return result of order confirming, true - order is confirmed, false otherwise
     * @throws ServiceException if there was exception during interaction with data source
     * @see OrderDtoForPharmacist
     */
    boolean confirmOrder(OrderDtoForPharmacist order) throws ServiceException;

    /**
     * Confirms client order
     *
     * @param id client order id.
     * @return result of order rejection, true - order is rejected, false otherwise
     * @throws ServiceException if there was exception during interaction with data source
     */
    boolean rejectOrder(int id) throws ServiceException;

    /**
     * Finds unhandled orders for client
     *
     * @param clientId      client id
     * @param startPosition pagination parameter that indicates start position from
     *                      which searching of client orders will start
     * @param offset        pagination parameter that indicates number of client orders
     *                      per page
     * @return unhandled client orders and number of pages to show them
     * @throws ServiceException if there was exception  during interaction with data source
     * @see ClientOrder
     * @see EntitySearchingResult
     */
    EntitySearchingResult<ClientOrder> findUnhandledClientOrders(int clientId, int startPosition, int offset)
            throws ServiceException;

    /**
     * Finds handled orders for client
     *
     * @param clientId      client id
     * @param startPosition pagination parameter that indicates start position from
     *                      which searching of client orders will start
     * @param offset        pagination parameter that indicates number of client orders
     *                      per page
     * @return handled client orders and number of pages to show them
     * @throws ServiceException if there was exception  during interaction with data source
     * @see ClientOrder
     * @see EntitySearchingResult
     */
    EntitySearchingResult<ClientOrder> findHandledClientOrders(int clientId, int startPosition, int offset)
            throws ServiceException;

    /**
     * Finds client order by id
     *
     * @param id client id
     * @return an object that contains all important data about client order.
     * @throws ServiceException if there was exception  during interaction with data source
     * @see ClientOrder
     * @see EntitySearchingResult
     */
    OrderDtoForPharmacist findByIdForPharmacist(int id) throws ServiceException;
}
