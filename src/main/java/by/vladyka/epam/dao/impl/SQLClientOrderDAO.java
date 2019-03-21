package by.vladyka.epam.dao.impl;

import by.vladyka.epam.dao.ClientOrderDAO;
import by.vladyka.epam.dao.exception.ConnectionPoolException;
import by.vladyka.epam.dao.exception.DAOException;
import by.vladyka.epam.dao.util.ConnectionPool;
import by.vladyka.epam.entity.ClientOrder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import static by.vladyka.epam.dao.util.SQLQuery.QUERY_CREATE_CLIENT_ORDER;
import static by.vladyka.epam.dao.util.SQLQuery.QUERY_LAST_INSERT_ID;

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

    @Override
    public boolean deleteById(int id) throws DAOException {
        return false;
    }

    @Override
    public List<ClientOrder> findAll() throws DAOException {
        return null;
    }

    @Override
    public int create(int clientId) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int clientOrderId = -1;
        try {
            con = pool.takeConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement(QUERY_CREATE_CLIENT_ORDER);
            ps.setDate(1, new java.sql.Date(new Date().getTime()));
            ps.setInt(2, clientId);
            ps.executeUpdate();

            ps = con.prepareStatement(QUERY_LAST_INSERT_ID);
            rs = ps.executeQuery();
            if (rs.next()) {
                clientOrderId = rs.getInt(1);
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
        return clientOrderId;
    }

//    private int ()
}
