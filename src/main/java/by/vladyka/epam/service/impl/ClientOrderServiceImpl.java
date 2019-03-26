package by.vladyka.epam.service.impl;

import by.vladyka.epam.dao.DAOProvider;
import by.vladyka.epam.dao.exception.DAOException;
import by.vladyka.epam.dao.impl.SQLClientOrderDAO;
import by.vladyka.epam.dto.EntitySearchingResult;
import by.vladyka.epam.dto.OrderDto;
import by.vladyka.epam.dto.OrderDtoForPharmacist;
import by.vladyka.epam.entity.ClientOrder;
import by.vladyka.epam.entity.Storage;
import by.vladyka.epam.service.ClientOrderService;
import by.vladyka.epam.service.exception.ServiceException;
import by.vladyka.epam.service.validator.impl.ClientOrderValidator;

import java.util.Map;

/**
 * Created by Vladyka Stas
 * on 21.03.2019 at 2:05
 **/
public class ClientOrderServiceImpl implements ClientOrderService {
    private ClientOrderValidator validator = new ClientOrderValidator();

    @Override
    public EntitySearchingResult<ClientOrder> findUnhandledClientOrders(int clientId, int startPosition, int offset) throws ServiceException {
        SQLClientOrderDAO clientOrderDAO = (SQLClientOrderDAO) DAOProvider.getInstance().getSQLClientOrderDAO();
        EntitySearchingResult<ClientOrder> orders;
        try {
            orders = clientOrderDAO.findUnhandledClientOrders(clientId, startPosition, offset);
            return orders;
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

    }

    @Override
    public EntitySearchingResult<ClientOrder> findHandledClientOrders(int clientId, int startPosition, int offset) throws ServiceException {
        SQLClientOrderDAO clientOrderDAO = (SQLClientOrderDAO) DAOProvider.getInstance().getSQLClientOrderDAO();
        EntitySearchingResult<ClientOrder> orders;
        try {
            orders = clientOrderDAO.findHandledClientOrders(clientId, startPosition, offset);
            return orders;
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean confirmOrder(OrderDtoForPharmacist order) throws ServiceException {
        SQLClientOrderDAO clientOrderDAO = (SQLClientOrderDAO) DAOProvider.getInstance().getSQLClientOrderDAO();
        boolean result;
        try {
            result = clientOrderDAO.confirmOrder(order);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public boolean rejectOrder(int id) throws ServiceException {
        SQLClientOrderDAO dao = (SQLClientOrderDAO) DAOProvider.getInstance().getSQLClientOrderDAO();
        boolean result;
        try {
            result = dao.rejectOrder(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public OrderDtoForPharmacist findByIdForPharmacist(int id) throws ServiceException {
        ClientOrderValidator validator = getValidator();
        boolean validationResult = validator.checkClientOrderIdAndSetMessage(id);
        if (!validationResult) {
            return null;
        }
        SQLClientOrderDAO clientOrderDAO = (SQLClientOrderDAO) DAOProvider.getInstance().getSQLClientOrderDAO();
        OrderDtoForPharmacist orderDtoForPharmacist;
        try {
            orderDtoForPharmacist = clientOrderDAO.findByIdForPharmacist(id);
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
        return orderDtoForPharmacist;
    }

    public EntitySearchingResult<ClientOrder> findUnhandledOrders(int start, int offset) throws ServiceException {
        SQLClientOrderDAO clientOrderDAO = (SQLClientOrderDAO) DAOProvider.getInstance().getSQLClientOrderDAO();
        EntitySearchingResult<ClientOrder> orders;
        try {
            orders = clientOrderDAO.findUnhandledOrders(start, offset);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return orders;
    }

    @Override
    public EntitySearchingResult<ClientOrder> findHandledOrders(int start, int offset) throws ServiceException {
        SQLClientOrderDAO dao = (SQLClientOrderDAO) DAOProvider.getInstance().getSQLClientOrderDAO();
        EntitySearchingResult<ClientOrder> clientOrders;
        try {
            clientOrders = dao.findHandledOrders(start, offset);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return clientOrders;
    }

    @Override
    public int buy(int clientId, OrderDto orderDto) throws ServiceException {
        int clientOrderId = -1;
        boolean validationResult = validator.checkClientIdAndSetMessage(clientId);
        if (!validationResult) {
            return clientOrderId;
        }
        DAOProvider provider = DAOProvider.getInstance();
        SQLClientOrderDAO clientOrderDAO = (SQLClientOrderDAO) provider.getSQLClientOrderDAO();
        double sum = countOrderSum(orderDto);
        try {
            clientOrderId = clientOrderDAO.create(clientId, sum);
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

    private double countOrderSum(OrderDto orderDto) {
        double sum = 0;
        for (Map.Entry<Storage, Integer> good :
                orderDto.getGoods().entrySet()) {
            double price = good.getKey().getRemedy().getPrice();
            sum += price * good.getValue();
        }
        return sum;
    }

    @Override
    public boolean delete(int id) throws ServiceException {
        return false;
    }

    @Override
    public ClientOrder findById(int id) throws ServiceException {
        return null;
    }


}
