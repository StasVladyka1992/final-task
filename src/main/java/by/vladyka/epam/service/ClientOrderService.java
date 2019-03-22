package by.vladyka.epam.service;

import by.vladyka.epam.dto.EntitySearchingResult;
import by.vladyka.epam.entity.ClientOrder;
import by.vladyka.epam.service.exception.ServiceException;

/**
 * Created by Vladyka Stas
 * on 21.03.2019 at 2:04
 **/
public interface ClientOrderService extends AbstractService <ClientOrder> {
    int buy(int clientId) throws ServiceException;
    EntitySearchingResult<ClientOrder> findUnhandledClientOrders(int start, int offset) throws ServiceException;
}
