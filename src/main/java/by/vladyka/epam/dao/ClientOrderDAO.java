package by.vladyka.epam.dao;

import by.vladyka.epam.dao.exception.DAOException;
import by.vladyka.epam.dto.EntitySearchingResult;
import by.vladyka.epam.entity.ClientOrder;

import java.util.Map;

/**
 * Created by Vladyka Stas
 * on 21.03.2019 at 1:15
 **/
public interface ClientOrderDAO extends AbstractDAO<ClientOrder> {
    int create (int clientId, double sum) throws DAOException;
    ClientOrder findByIdAndSetReceipts(int id, int clientId) throws DAOException;
    EntitySearchingResult<ClientOrder> findUnhandledClientOrders(int start, int offset) throws DAOException;
    Map<Integer, Integer> findStorageQuantityForClientOrder(int clientOrderId) throws DAOException;
}
