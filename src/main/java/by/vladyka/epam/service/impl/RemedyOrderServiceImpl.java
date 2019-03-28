package by.vladyka.epam.service.impl;

import by.vladyka.epam.dao.DAOProvider;
import by.vladyka.epam.dao.RemedyOrderDAO;
import by.vladyka.epam.dao.exception.DAOException;
import by.vladyka.epam.dto.OrderDto;
import by.vladyka.epam.entity.ClientOrder;
import by.vladyka.epam.service.RemedyOrderService;
import by.vladyka.epam.service.exception.ServiceException;

/**
 * Created by Vladyka Stas
 * on 21.03.2019 at 12:35
 **/
public class RemedyOrderServiceImpl implements RemedyOrderService {

    @Override
    public boolean create(OrderDto orderDto, int clientOrderId) throws ServiceException {
        DAOProvider provider = DAOProvider.getInstance();
        RemedyOrderDAO remedyOrderDAO = provider.getSQLRemedyOrderDAO();
        boolean result;
        try {
            result = remedyOrderDAO.create(orderDto, clientOrderId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public void setReceipts(ClientOrder order) throws ServiceException {
        DAOProvider provider = DAOProvider.getInstance();
        RemedyOrderDAO dao = provider.getSQLRemedyOrderDAO();
        try {
            dao.setReceiptsToRemedyOrders(order);
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }
}
