package by.vladyka.epam.service.impl;

import by.vladyka.epam.dao.DAOProvider;
import by.vladyka.epam.dao.exception.DAOException;
import by.vladyka.epam.dao.impl.SQLClientOrderDAO;
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
        return null;
    }

    @Override
    public List<ClientOrder> findAll() throws ServiceException {
        return null;
    }

    @Override
    public int buy(int clientId) throws ServiceException {
        int clientOrderId = -1;
        boolean validationResult = validator.checkId(clientId);
        if (!validationResult) {
            return clientOrderId;
        }
        DAOProvider provider = DAOProvider.getInstance();
        SQLClientOrderDAO clientOrderDAO = (SQLClientOrderDAO) provider.getSQLClientOrderDAO();
        try {
            int result = clientOrderDAO.create(clientId);
            if (result == 1) {
                clientOrderId = clientOrderDAO.getLastInsertedId();
            }
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
