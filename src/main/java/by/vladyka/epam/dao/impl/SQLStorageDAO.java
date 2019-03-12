package by.vladyka.epam.dao.impl;

import by.vladyka.epam.dao.RemedyUtil;
import by.vladyka.epam.dao.StorageDAO;
import by.vladyka.epam.dao.exception.ConnectionPoolException;
import by.vladyka.epam.dao.exception.DAOException;
import by.vladyka.epam.dao.util.ConnectionPool;
import by.vladyka.epam.entity.Storage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static by.vladyka.epam.dao.util.SQLQuery.QUERY_DELETE_STORAGE_POSITION;
import static by.vladyka.epam.dao.util.SQLQuery.QUERY_GET_FULL_STORAGE_POSITION;

/**
 * Created by Vladyka Stas
 * on 11.03.2019 at 13:01
 **/
public class SQLStorageDAO implements StorageDAO, RemedyUtil {
    private static final ConnectionPool pool = ConnectionPool.getInstance();

    @Override
    public Storage findById(int id) throws DAOException {
        Storage storage = new Storage();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = pool.takeConnection();
            ps = con.prepareStatement(QUERY_GET_FULL_STORAGE_POSITION);
            ps.setInt(1, id);
            while (rs.next()) {
                Integer quantity = rs.getInt(6);
                storage.setRemedy(createRemedy(rs));
                storage.setRemedyLeft(quantity);
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
    public boolean deleteById(int id) throws DAOException {
        return deleteHelper(id, QUERY_DELETE_STORAGE_POSITION, pool);
    }

    @Override
    public List<Storage> findAll() throws DAOException {
        return null;
    }


}
