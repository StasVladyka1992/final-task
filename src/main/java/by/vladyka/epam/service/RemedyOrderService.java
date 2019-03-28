package by.vladyka.epam.service;

import by.vladyka.epam.dto.OrderDto;
import by.vladyka.epam.entity.ClientOrder;
import by.vladyka.epam.entity.RemedyOrder;
import by.vladyka.epam.service.exception.ServiceException;

/**
 * An interface that declare activities with {@link RemedyOrder}
 *
 * @author Stas Vladyka
 * @version 1.0
 **/
public interface RemedyOrderService {

    /**
     * Creates new remedy order which is a part of client order
     *
     * @param orderDto      an object that contains data about client order.
     * @param clientOrderId client order id
     * @return result of remedy order creation, true - remedy order is created, false otherwise
     * @throws ServiceException if there was exception during interaction with data source
     * @see OrderDto
     */
    boolean create(OrderDto orderDto, int clientOrderId) throws ServiceException;

    /**
     * Set receipts to each remedy order if remedy requires receipt
     *
     * @param order client order
     * @throws ServiceException if there was exception during interaction with data source
     * @see ClientOrder
     */
    void setReceipts(ClientOrder order) throws ServiceException;
}
