package by.vladyka.epam.dao.impl;

import by.vladyka.epam.dao.StorageDAO;
import by.vladyka.epam.dao.exception.ConnectionPoolException;
import by.vladyka.epam.dao.exception.DAOException;
import by.vladyka.epam.dao.util.ConnectionPool;
import by.vladyka.epam.dao.util.RemedyUtil;
import by.vladyka.epam.dto.EntitySearchingResult;
import by.vladyka.epam.entity.Remedy;
import by.vladyka.epam.entity.Storage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static by.vladyka.epam.dao.util.DBColumn.REMEDY_LEFT;
import static by.vladyka.epam.dao.util.SQLQuery.*;

/**
 * Created by Vladyka Stas
 * on 11.03.2019 at 13:01
 **/
public class SQLStorageDAO implements StorageDAO, RemedyUtil {
    private static final ConnectionPool pool = ConnectionPool.getInstance();

    @Override
    public Storage findById(int id) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Storage storage = null;
        try {
            con = pool.takeConnection();
            ps = con.prepareStatement(QUERY_GET_FULL_STORAGE_POSITION);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            int remedyLeft = -1;
            while (rs.next()) {
                storage = new Storage();
                String remedyLeftText = rs.getString(REMEDY_LEFT);
                if (remedyLeftText != null) {
                    remedyLeft = Integer.parseInt(remedyLeftText);
                    storage.setRemedyLeft(remedyLeft);
                } else {
                    storage.setRemedyLeft(remedyLeft);
                }
                storage.setRemedy(createRemedy(rs));
                storage.setId(rs.getInt(7));
            }
        } catch (SQLException ex) {
            throw new DAOException(ex);
        } catch (ConnectionPoolException ex) {
            throw new DAOException(ex);
        } finally {
            pool.closeConnection(con, ps, rs);
        }
        return storage;
    }

    @Override
    public EntitySearchingResult findFromStartPosition(String name, int start, int offset) throws DAOException {
        EntitySearchingResult result = new EntitySearchingResult();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = pool.takeConnection();
            ps = con.prepareStatement(QUERY_FIND_REMEDIES_BY_NAME);
            ps.setString(1, "%" + name + "%");
            ps.setInt(2, start);
            ps.setInt(3, offset);
            rs = ps.executeQuery();
            while (rs.next()) {
                Remedy remedy = createRemedy(rs);
                String remedyLeftText = rs.getString(REMEDY_LEFT);
                int remedyLeft = -1;
                Storage st = new Storage();
                if (remedyLeftText != null) {
                    remedyLeft = Integer.parseInt(remedyLeftText);
                    st.setRemedyLeft(remedyLeft);
                } else {
                    st.setRemedyLeft(remedyLeft);
                }
                st.setRemedy(remedy);
                result.getFoundEntities().add(st);
            }
            result.setFoundEntitiesNumber(getFoundRemediesNumber(name));
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            pool.closeConnection(con, ps, rs);
        }
        return result;
    }

    @Override
    public boolean deleteById(int id) throws DAOException {
        return deleteHelper(id, QUERY_DELETE_STORAGE_POSITION, pool);
    }

    @Override
    public List<Storage> findAll() throws DAOException {
        return null;
    }

    @Override
    public boolean create(int remedyId, int remedyLeft) throws DAOException {
        int insertionResult = createAndExecutePreparedStatement(QUERY_ADD_REMEDY_TO_STORAGE, remedyId, remedyLeft);
        return insertionResult == 1;
    }

    @Override
    public boolean update(int remedyId, int remedyLeft) throws DAOException {
        int updateResult = createAndExecutePreparedStatement(QUERY_UPDATE_STORAGE_POSITION, remedyId, remedyLeft);
        return updateResult == 1;
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

    private int createAndExecutePreparedStatement(String query, int remedyId,
                                                  int remedyLeft) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        int operationResult;
        try {
            con = pool.takeConnection();
            ps = con.prepareStatement(query);
            ps.setInt(1, remedyLeft);
            ps.setInt(2, remedyId);
            operationResult = ps.executeUpdate();
        } catch (SQLException ex) {
            throw new DAOException(ex);
        } catch (ConnectionPoolException ex) {
            throw new DAOException(ex);
        } finally {
            pool.closeConnection(con, ps);
        }
        return operationResult;
    }
}
