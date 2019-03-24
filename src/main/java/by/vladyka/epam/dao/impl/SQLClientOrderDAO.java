package by.vladyka.epam.dao.impl;

import by.vladyka.epam.dao.ClientOrderDAO;
import by.vladyka.epam.dao.exception.ConnectionPoolException;
import by.vladyka.epam.dao.exception.DAOException;
import by.vladyka.epam.dao.util.ConnectionPool;
import by.vladyka.epam.dto.EntitySearchingResult;
import by.vladyka.epam.entity.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static by.vladyka.epam.dao.util.SQLDaoAssistant.getFoundEntitiesNumber;
import static by.vladyka.epam.dao.util.SQLDaoAssistant.setStartPositionAndOffset;
import static by.vladyka.epam.dao.util.SQLQuery.*;

/**
 * Created by Vladyka Stas
 * on 21.03.2019 at 1:15
 **/
public class SQLClientOrderDAO implements ClientOrderDAO {
    private static final ConnectionPool pool = ConnectionPool.getInstance();

    public ClientOrder findByIdAndSetReceipts(int id, int clientId) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ClientOrder clientOrder = null;
        try {
            setReceiptsToRemedyOrders(id, clientId);
            con = pool.takeConnection();
            ps = con.prepareStatement(QUERY_FIND_CLIENT_ORDER_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            User user = null;
            while (rs.next()) {
                if (clientOrder == null) {
                    clientOrder = new ClientOrder();
                    clientOrder.setRemedyOrders(new ArrayList<>());
                    user = new User();
                    user.setId(clientId);
                }

                Remedy remedy = new Remedy();
                remedy.setName(rs.getString(1));
                remedy.setDescription(rs.getString(2));
                remedy.setReceiptRequired(rs.getBoolean(3));

                RemedyOrder remedyOrder = new RemedyOrder();
                remedyOrder.setQuantity(rs.getInt(4));
                clientOrder.setId(id);
                clientOrder.setClient(user);
                clientOrder.setCreatedOn(rs.getTimestamp(5));
                clientOrder.setSum(rs.getDouble(6));


                String receiptStatus = rs.getString(7);
                if (receiptStatus != null) {
                    Receipt receipt = new Receipt();
                    receipt.setId(rs.getInt(8));
                    receipt.setStatus(Receipt.Status.valueOf(receiptStatus));
                    remedyOrder.setReceipt(receipt);
                }

                remedyOrder.setRemedy(remedy);
                clientOrder.getRemedyOrders().add(remedyOrder);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            pool.closeConnection(con, ps, rs);
        }
        return clientOrder;
    }

    public Map<Integer, Integer> findStorageQuantityForClientOrder(int clientOrderId) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Map<Integer, Integer> storageQuantityOfRemedyOrders = new HashMap<>();
        try {
            con = pool.takeConnection();
            ps = con.prepareStatement(QUERY_FIND_STORAGE_QUANTITY_FOR_REMEDY_ORDERS);
            ps.setInt(1, clientOrderId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Integer remedyOrderId = rs.getInt(1);
                Integer storageQuantity = rs.getInt(2);
                storageQuantityOfRemedyOrders.put(remedyOrderId, storageQuantity);
            }
        } catch (SQLException | ConnectionPoolException ex) {
            throw new DAOException(ex);
        } finally {
            pool.closeConnection(con, ps, rs);
        }
        return storageQuantityOfRemedyOrders;
    }

    private void setReceiptsToRemedyOrders(int id, int clientId) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = pool.takeConnection();
            ps = con.prepareStatement(QUERY_SET_RECEIPTS_TO_CLIENT_ORDER);
            ps.setInt(1, clientId);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            pool.closeConnection(con, ps);
        }
    }

    public EntitySearchingResult<ClientOrder> findUnhandledClientOrders(int start, int offset) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int unhandledClientOrdersNumber;
        List<ClientOrder> clientOrders;
        try {
            unhandledClientOrdersNumber = getFoundEntitiesNumber(QUERY_COUNT_UNHANDLED_CLIENT_ORDERS, pool);
            con = pool.takeConnection();
            ps = con.prepareStatement(QUERY_FIND_UNHANDLED_CLIENT_ORDERS);
            setStartPositionAndOffset(ps, start, offset);
            rs = ps.executeQuery();
            clientOrders = new ArrayList<>();
            while (rs.next()) {
                ClientOrder clientOrder = new ClientOrder();
                clientOrder.setId(rs.getInt(1));
                clientOrder.setCreatedOn(new Date(rs.getTimestamp(2).getTime()));
                clientOrder.setStatus(ClientOrder.ClientOrderStatus.valueOf(rs.getString(3)));
                User user = new User();
                user.setId(rs.getInt(4));
                clientOrder.setClient(user);
                clientOrders.add(clientOrder);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            pool.closeConnection(con, ps, rs);
        }
        EntitySearchingResult<ClientOrder> unhandledClientOrders = new EntitySearchingResult<>();
        unhandledClientOrders.setFoundEntities(clientOrders);
        unhandledClientOrders.setFoundEntitiesNumber(unhandledClientOrdersNumber);
        return unhandledClientOrders;
    }

    @Override
    public int create(int clientId, double sum) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int insertResult;
        int lastId = -1;
        try {
            con = pool.takeConnection();
            ps = con.prepareStatement(QUERY_CREATE_CLIENT_ORDER);
            ps.setInt(1, clientId);
            ps.setDouble(2, sum);
            insertResult = ps.executeUpdate();
        } catch (SQLException | ConnectionPoolException ex) {
            try {
                con.rollback();
            } catch (SQLException e) {
                throw new DAOException(e);
            }
            throw new DAOException(ex);
        } finally {
            pool.closeConnection(con, ps, rs);
        }
        if (insertResult == 1) {
            lastId = getLastInsertId();
        }
        return lastId;
    }

    @Override
    public boolean deleteById(int id) throws DAOException {
        return false;
    }

    @Override
    public List<ClientOrder> findAll() throws DAOException {
        return null;
    }

    @Override
    public ClientOrder findById(int id) throws DAOException {
        return null;
    }

    private int getLastInsertId() throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = pool.takeConnection();
            ps = con.prepareStatement(QUERY_GET_LAST_CLIENT_ORDER_ID);
            rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt(1);
                return id;
            } else return 0;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            pool.closeConnection(con, ps, rs);
        }
    }


}
