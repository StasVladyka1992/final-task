package by.vladyka.epam.service;

import by.vladyka.epam.entity.ClientOrder;
import by.vladyka.epam.service.exception.ServiceException;

/**
 * Created by Vladyka Stas
 * on 21.03.2019 at 2:04
 **/
public interface ClientOrderService extends AbstractService <ClientOrder> {
    int create(int clientId) throws ServiceException;
}
