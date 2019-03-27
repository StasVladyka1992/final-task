package by.vladyka.epam.service;

import by.vladyka.epam.dto.EntitySearchingResult;
import by.vladyka.epam.dto.OrderDto;
import by.vladyka.epam.dto.OrderDtoForPharmacist;
import by.vladyka.epam.entity.ClientOrder;
import by.vladyka.epam.service.exception.ServiceException;

/**
 * Created by Vladyka Stas
 * on 21.03.2019 at 2:04
 **/
public interface ClientOrderService extends AbstractService<ClientOrder> {
    int buy(int clientId, OrderDto orderDto) throws ServiceException;

    EntitySearchingResult<ClientOrder> findUnhandledOrders(int start, int offset) throws ServiceException;

    EntitySearchingResult<ClientOrder> findHandledOrders(int start, int offset) throws ServiceException;

    boolean confirmOrder(OrderDtoForPharmacist order) throws ServiceException;

    boolean rejectOrder(int id) throws ServiceException;

    EntitySearchingResult<ClientOrder> findUnhandledClientOrders(int clientId, int startPosition, int offset)
            throws ServiceException;

    EntitySearchingResult<ClientOrder> findHandledClientOrders(int clientId, int startPosition, int offset)
            throws ServiceException;

    OrderDtoForPharmacist findByIdForPharmacist(int id) throws ServiceException;
}
