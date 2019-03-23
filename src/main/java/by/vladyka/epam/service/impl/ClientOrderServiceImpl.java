package by.vladyka.epam.service.impl;

import by.vladyka.epam.dao.DAOProvider;
import by.vladyka.epam.dao.exception.DAOException;
import by.vladyka.epam.dao.impl.SQLClientOrderDAO;
import by.vladyka.epam.dto.EntitySearchingResult;
import by.vladyka.epam.entity.ClientOrder;
import by.vladyka.epam.service.ClientOrderService;
import by.vladyka.epam.service.exception.ServiceException;
import by.vladyka.epam.service.validator.impl.ClientOrderValidator;

import java.util.List;

/**
 * Created by Vladyka Stas
 * on 21.03.2019 at 2:05
 **/
public class ClientOrderServiceImpl implements ClientOrderService {
    private ClientOrderValidator validator = new ClientOrderValidator();

    @Override
    public boolean delete(int id) throws ServiceException {
        return false;
    }

    @Override
    public ClientOrder findById(int id) throws ServiceException {
        ClientOrderValidator validator = getValidator();
        boolean validationResult = validator.checkClientOrderIdAndSetMessage(id);
        if (!validationResult) {
            return null;
        }
        ClientOrder order = null;
        SQLClientOrderDAO clientOrderDAO = (SQLClientOrderDAO) DAOProvider.getInstance().getSQLClientOrderDAO();
        try {
           order = clientOrderDAO.findById(id);
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
        return order;
    }

    public EntitySearchingResult<ClientOrder> findUnhandledClientOrders(int start, int offset) throws ServiceException {
        SQLClientOrderDAO clientOrderDAO = (SQLClientOrderDAO) DAOProvider.getInstance().getSQLClientOrderDAO();
        EntitySearchingResult<ClientOrder> orders;
        try {
            orders = clientOrderDAO.findUnhandledClientOrders(start, offset);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return orders;
    }

    @Override
    public List<ClientOrder> findAll() throws ServiceException {
        return null;
    }

    @Override
    public int buy(int clientId) throws ServiceException {
        int clientOrderId = -1;
        boolean validationResult = validator.checkClientIdAndSetMessage(clientId);
        if (!validationResult) {
            return clientOrderId;
        }
        DAOProvider provider = DAOProvider.getInstance();
        SQLClientOrderDAO clientOrderDAO = (SQLClientOrderDAO) provider.getSQLClientOrderDAO();
        try {
            clientOrderId = clientOrderDAO.create(clientId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return clientOrderId;
    }

    public ClientOrderValidator getValidator() {
        return validator;
    }

    public void setValidator(ClientOrderValidator validator) {
        this.validator = validator;
    }
}
