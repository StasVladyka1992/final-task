package by.vladyka.epam.dao.impl;

import by.vladyka.epam.dao.RemedyDAO;
import by.vladyka.epam.dao.exception.ConnectionPoolException;
import by.vladyka.epam.dao.exception.DAOException;
import by.vladyka.epam.dao.util.ConnectionPool;
import by.vladyka.epam.dao.util.SQLDaoAssistant;
import by.vladyka.epam.entity.Remedy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static by.vladyka.epam.dao.util.SQLDaoAssistant.createRemedy;
import static by.vladyka.epam.dao.util.SQLQuery.*;

/**
 * Created by Vladyka Stas
 * on 26.02.2019 at 1:58
 **/
public class SQLRemedyDAO implements RemedyDAO {
    private static final ConnectionPool pool = ConnectionPool.getInstance();

    @Override
    public Remedy findById(int id) throws DAOException {
        Remedy remedy = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = pool.takeConnection();
            ps = con.prepareStatement(QUERY_FIND_REMEDY_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                remedy = createRemedy(rs);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } catch (ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            pool.closeConnection(con, ps, rs);
        }
        return remedy;
    }

    @Override
    public List<Remedy> findAll() {
        return null;
    }

    @Override
    public boolean update(int id, String name, String description, double price, boolean receiptRequired) throws DAOException {
        String query = QUERY_UPDATE_REMEDY + id;
        boolean result = queryExecutor(query, name, description, price,
                receiptRequired);
        return result;
    }


    @Override
    public boolean create(String name, String description, double price, boolean receiptRequired) throws DAOException {
        boolean result = queryExecutor(QUERY_ADD_REMEDY, name, description, price,
                receiptRequired);
        return result;
    }

    @Override
    public boolean deleteById(int id) throws DAOException {
        return deleteHelper(id, QUERY_DELETE_REMEDY, pool);
    }

    private boolean queryExecutor(String query, String name,
                                  String description, double price, boolean
                                                                       receiptRequired) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        int insertionResult;
        try {
            con = pool.takeConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, description);
            ps.setDouble(3, price);
            ps.setBoolean(4, receiptRequired);
            insertionResult = ps.executeUpdate();
        } catch (SQLException ex) {
            throw new DAOException(ex);
        } catch (ConnectionPoolException ex) {
            throw new DAOException(ex);
        } finally {
            pool.closeConnection(con, ps);
        }
        return insertionResult == 1;
    }

}
