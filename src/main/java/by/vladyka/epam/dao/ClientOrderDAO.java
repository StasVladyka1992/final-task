package by.vladyka.epam.dao;

import by.vladyka.epam.dao.exception.DAOException;
import by.vladyka.epam.dto.EntitySearchingResult;
import by.vladyka.epam.dto.OrderDto;
import by.vladyka.epam.dto.OrderDtoForPharmacist;
import by.vladyka.epam.entity.ClientOrder;

import java.util.List;

/**
 * Created by Vladyka Stas
 * on 21.03.2019 at 1:15
 **/
public interface ClientOrderDAO extends AbstractDAO<ClientOrder> {
    boolean create(int clientId, double sum, OrderDto orderDto) throws DAOException;

    List<ClientOrder> findUnhandledOrders() throws DAOException;

    EntitySearchingResult<ClientOrder> findHandledOrders(int start, int offset) throws DAOException;

    boolean confirmOrder(OrderDtoForPharmacist order) throws DAOException;

    EntitySearchingResult<ClientOrder> findUnhandledClientOrders(int clientId, int startPosition, int offset)
            throws DAOException;

    EntitySearchingResult<ClientOrder> findHandledClientOrders(int clientId, int startPosition, int offset)
            throws DAOException;

    boolean rejectOrder(int id) throws DAOException;

}
