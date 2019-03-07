package by.vladyka.epam.dao.impl;

import by.vladyka.epam.dao.AbstractDAO;
import by.vladyka.epam.dao.RemedyDAO;
import by.vladyka.epam.dao.exception.ConnectionPoolException;
import by.vladyka.epam.dao.exception.DAOException;
import by.vladyka.epam.dao.util.ConnectionPool;
import by.vladyka.epam.entity.Remedy;
import by.vladyka.epam.entity.RemedySearchingResult;

import java.sql.*;
import java.util.Map;

import static by.vladyka.epam.controller.util.ParameterName.*;
import static by.vladyka.epam.dao.util.SQLQuery.*;

/**
 * Created by Vladyka Stas
 * on 26.02.2019 at 1:58
 **/
public class SQLRemedyDAO extends AbstractDAO implements RemedyDAO {
    private static final ConnectionPool pool = ConnectionPool.getInstance();

    public RemedySearchingResult findRemedy(String name, int start, int offset) throws DAOException {
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
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            closeConnection(con, ps, rs);
        }
        return result;
    }

    private Remedy createRemedy(ResultSet resultSet) throws SQLException {
        Remedy remedy = new Remedy();
        remedy.setIdRemedy(resultSet.getInt(ID_REMEDY));
        remedy.setName(resultSet.getString(REMEDY_NAME));
        remedy.setPacking(Remedy.Packing.getEnumElementByValue(resultSet.getString(PACKING)));
        remedy.setMaker(resultSet.getString(MAKER));
        remedy.setQuantity(resultSet.getInt(QUANTITY));
        remedy.setPrice(resultSet.getDouble(PRICE));
        remedy.setReceipt(resultSet.getString(RECEIPT).charAt(0));
        return remedy;
    }

    private int getFoundRemediesNumber(String remedyName) throws DAOException {
        int foundRemediesNumber = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = pool.takeConnection();
            ps = con.prepareStatement(QUERY_COUNT_SIMULAR_REMEDIES);
            ps.setString(1, "%" + remedyName + "%");
            rs = ps.executeQuery();
            if (rs.next()) {
                foundRemediesNumber = rs.getInt(1);
            }
        } catch (SQLException | ConnectionPoolException ex) {
            throw new DAOException(ex);
        } finally {
            closeConnection(con, ps, rs);
        }
        return foundRemediesNumber;
    }

    public boolean updateRemedy(Map<String, String> parameters) {
        return true;
    }

    public boolean addRemedy(Map<String, String> parameters) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        int insertionResult;
        try {
            con = pool.takeConnection();
            ps = con.prepareStatement(QUERY_ADD_REMEDY);
            ps.setString(1, parameters.get(REMEDY_NAME));
            ps.setString(2, parameters.get(PACKING));
            ps.setString(3, parameters.get(MAKER));
            ps.setString(4, parameters.get(QUANTITY));
            ps.setString(5, parameters.get(PRICE));
            ps.setString(6, parameters.get(RECEIPT));
            insertionResult = ps.executeUpdate();
        } catch (SQLException | ConnectionPoolException ex) {
            throw new DAOException(ex);
        } finally {
            closeConnection(con, ps);
        }
        return insertionResult==1;
    }

    public boolean deleteRemedy(Map<String, String> parameters) {
        return true;
    }
}
