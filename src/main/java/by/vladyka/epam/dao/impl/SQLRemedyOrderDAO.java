package by.vladyka.epam.dao.impl;

import by.vladyka.epam.dao.RemedyOrderDAO;
import by.vladyka.epam.dao.exception.ConnectionPoolException;
import by.vladyka.epam.dao.exception.DAOException;
import by.vladyka.epam.dao.util.ConnectionPool;
import by.vladyka.epam.dto.OrderDto;
import by.vladyka.epam.entity.ClientOrder;
import by.vladyka.epam.entity.RemedyOrder;
import by.vladyka.epam.entity.Storage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

import static by.vladyka.epam.dao.util.SQLQuery.QUERY_CREATE_REMEDY_ORDER;
import static by.vladyka.epam.dao.util.SQLQuery.QUERY_SET_RECEIPTS_TO_CLIENT_ORDER;

/**
 * Created by Vladyka Stas
 * on 21.03.2019 at 1:08
 **/
public class SQLRemedyOrderDAO implements RemedyOrderDAO {
    private static final ConnectionPool pool = ConnectionPool.getInstance();


    public void setReceiptsToRemedyOrders(ClientOrder order) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        int[] batchResult;
        try {
            con = pool.takeConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement(QUERY_SET_RECEIPTS_TO_CLIENT_ORDER);
            for (RemedyOrder remedyOrder :
                    order.getRemedyOrders()) {
                ps.setInt(1, order.getClient().getId());
                ps.setInt(2, remedyOrder.getRemedy().getId());
                ps.setInt(3, remedyOrder.getId());
                ps.addBatch();
            }
            batchResult = ps.executeBatch();
            for (int i1 : batchResult) {
                if (i1 != 1) {
                    throw new SQLException("Not all receipts were set to remedy orders");
                }
            }
            con.commit();
        } catch (SQLException | ConnectionPoolException e) {
            doRollback(con);
            throw new DAOException(e);
        } finally {
            pool.closeConnection(con, ps);
        }
    }


}
