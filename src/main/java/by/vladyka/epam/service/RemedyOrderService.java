package by.vladyka.epam.service;

import by.vladyka.epam.dto.OrderDto;
import by.vladyka.epam.entity.RemedyOrder;
import by.vladyka.epam.service.exception.ServiceException;

/**
 * Created by Vladyka Stas
 * on 21.03.2019 at 12:36
 **/
public interface RemedyOrderService extends AbstractService<RemedyOrder> {
    boolean create(OrderDto orderDto, int clientOrderId) throws ServiceException;
}
