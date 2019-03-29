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
     * Set receipts to each remedy order if remedy requires receipt
     *
     * @param order client order
     * @throws ServiceException if there was exception during interaction with data source
     * @see ClientOrder
     */
    void setReceipts(ClientOrder order) throws ServiceException;
}
