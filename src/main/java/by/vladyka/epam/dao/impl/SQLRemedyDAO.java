package by.vladyka.epam.dao.impl;

import by.vladyka.epam.dao.RemedyDAO;
import by.vladyka.epam.dao.RemedyUtil;
import by.vladyka.epam.dao.exception.ConnectionPoolException;
import by.vladyka.epam.dao.exception.DAOException;
import by.vladyka.epam.dao.util.ConnectionPool;
import by.vladyka.epam.entity.Remedy;
import by.vladyka.epam.entity.RemedySearchingResult;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static by.vladyka.epam.dao.util.SQLQuery.*;

/**
 * Created by Vladyka Stas
 * on 26.02.2019 at 1:58
 **/
public class SQLRemedyDAO implements RemedyDAO, RemedyUtil {
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
        Connection con = null;
        PreparedStatement ps = null;
        int insertionResult;
        try {
            con = pool.takeConnection();
            ps = createAndSetRemedyParamToPreparedStatement(con, QUERY_UPDATE_REMEDY, name, description, price,
                    receiptRequired);
            ps.setInt(5, id);
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

    public RemedySearchingResult findFromStartPosition(String name, int start, int offset) throws DAOException {
        RemedySearchingResult result = new RemedySearchingResult();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = pool.takeConnection();
            ps = con.prepareStatement(QUERY_FIND_REMEDY);
            ps.setString(1, "%" + name + "%");
            ps.setInt(2, start);
            ps.setInt(3, offset);
            rs = ps.executeQuery();
            while (rs.next()) {
                Remedy remedy = createRemedy(rs);
                result.getRemedies().add(remedy);
            }
            result.setFoundRemediesNumber(getFoundRemediesNumber(name));
        } catch (SQLException e) {
            throw new DAOException(e);
        } catch (ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            pool.closeConnection(con, ps, rs);
        }
        return result;
    }

    private int getFoundRemediesNumber(String name) throws DAOException {
        int foundRemediesNumber = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = pool.takeConnection();
            ps = con.prepareStatement(QUERY_COUNT_SIMULAR_REMEDIES);
            ps.setString(1, "%" + name + "%");
            rs = ps.executeQuery();
            if (rs.next()) {
                foundRemediesNumber = rs.getInt(1);
            }
        } catch (SQLException | ConnectionPoolException ex) {
            throw new DAOException(ex);
        } finally {
            pool.closeConnection(con, ps, rs);
        }
        return foundRemediesNumber;
    }

    @Override
    public boolean create(String name, String description, double price, boolean receiptRequired) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        int insertionResult;
        try {
            con = pool.takeConnection();
            ps = createAndSetRemedyParamToPreparedStatement(con, QUERY_ADD_REMEDY, name, description, price,
                    receiptRequired);
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

    @Override
    public boolean deleteById(int id) throws DAOException {
        return deleteHelper(id, QUERY_DELETE_REMEDY, pool);
    }

    private PreparedStatement createAndSetRemedyParamToPreparedStatement(Connection con, String query, String name,
                                                                         String description, double price, boolean
                                                                                 receiptRequired) throws SQLException {
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, name);
        ps.setString(2, description);
        ps.setDouble(3, price);
        ps.setBoolean(4, receiptRequired);
        return ps;
    }
}
