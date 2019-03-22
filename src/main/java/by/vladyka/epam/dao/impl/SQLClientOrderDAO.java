package by.vladyka.epam.dao.impl;

import by.vladyka.epam.dao.ClientOrderDAO;
import by.vladyka.epam.dao.exception.ConnectionPoolException;
import by.vladyka.epam.dao.exception.DAOException;
import by.vladyka.epam.dao.util.ConnectionPool;
import by.vladyka.epam.dto.EntitySearchingResult;
import by.vladyka.epam.entity.ClientOrder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static by.vladyka.epam.dao.util.SQLDaoAssistant.getFoundEntitiesNumber;
import static by.vladyka.epam.dao.util.SQLDaoAssistant.setStartPositionAndOffset;
import static by.vladyka.epam.dao.util.SQLQuery.*;

/**
 * Created by Vladyka Stas
 * on 21.03.2019 at 1:15
 **/
public class SQLClientOrderDAO implements ClientOrderDAO {
    private static final ConnectionPool pool = ConnectionPool.getInstance();

    @Override
    public ClientOrder findById(int id) throws DAOException {
        return null;
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
                //TODO разобраться с датой
                clientOrder.setCreatedOn(rs.getDate(2));
                clientOrder.setFinishedOn(rs.getDate(3));
                clientOrder.setStatus(ClientOrder.ClientOrderStatus.valueOf(rs.getString(4)));
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
    public int create(int clientId) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int insertResult;
        int lastId = -1;
        try {
            con = pool.takeConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement(QUERY_CREATE_CLIENT_ORDER);
            ps.setDate(1, new java.sql.Date(new Date().getTime()));
            ps.setInt(2, clientId);
            insertResult = ps.executeUpdate();
            if (insertResult == 1) {
                lastId = getLastInsertId();
            }
            con.commit();
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

    private int getLastInsertId() throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = pool.takeConnection();
            ps = con.prepareStatement(QUERY_GET_LAST_CLIENT_ORDER_ID);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            } else return 0;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            pool.closeConnection(con, ps, rs);
        }
    }

}
