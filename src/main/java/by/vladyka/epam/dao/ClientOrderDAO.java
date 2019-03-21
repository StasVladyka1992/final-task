package by.vladyka.epam.dao;

import by.vladyka.epam.dao.exception.DAOException;
import by.vladyka.epam.entity.ClientOrder;

/**
 * Created by Vladyka Stas
 * on 21.03.2019 at 1:15
 **/
public interface ClientOrderDAO extends AbstractDAO<ClientOrder> {
    int create (int clientId) throws DAOException;
    int getLastInsertedId() throws DAOException;
}
