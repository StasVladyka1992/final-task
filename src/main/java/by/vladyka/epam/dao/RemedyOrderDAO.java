package by.vladyka.epam.dao;

import by.vladyka.epam.dao.exception.DAOException;
import by.vladyka.epam.dto.OrderDto;
import by.vladyka.epam.entity.ClientOrder;
import by.vladyka.epam.entity.RemedyOrder;

/**
 * Created by Vladyka Stas
 * on 12.03.2019 at 11:16
 **/
public interface RemedyOrderDAO extends AbstractDAO<RemedyOrder> {
    boolean create(OrderDto orderDto, int clientOrderId) throws DAOException;

    void setReceiptsToRemedyOrders(ClientOrder order) throws DAOException;
}
