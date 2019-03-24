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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public OrderDtoForPharmacist findByIdAndSetReceipts(int id, int clientId) throws ServiceException {
        ClientOrderValidator validator = getValidator();
        boolean validationResult = validator.checkClientOrderIdAndSetMessage(id);
        if (!validationResult) {
            return null;
        }
        ClientOrder order;
        SQLClientOrderDAO clientOrderDAO = (SQLClientOrderDAO) DAOProvider.getInstance().getSQLClientOrderDAO();
        OrderDtoForPharmacist orderDtoForPharmacist = null;
        try {
            order = clientOrderDAO.findByIdAndSetReceipts(id, clientId);
            if (order != null) {
                Map<Integer, Integer> storageQuantityOfRemedyOrders = clientOrderDAO.
                        findStorageQuantityForClientOrder(order.getId());
                orderDtoForPharmacist = new OrderDtoForPharmacist(order,
                        storageQuantityOfRemedyOrders);
            }
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
        return orderDtoForPharmacist;
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
}
