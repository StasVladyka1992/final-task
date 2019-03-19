package by.vladyka.epam.dao;

import by.vladyka.epam.dao.exception.ConnectionPoolException;
import by.vladyka.epam.dao.exception.DAOException;
import by.vladyka.epam.dao.util.ConnectionPool;
import by.vladyka.epam.entity.AbstractEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Vladyka Stas
 * on 09.03.2019 at 20:13
 **/
public interface AbstractDAO<T extends AbstractEntity> {
    T findById(int id) throws DAOException;

    boolean deleteById(int id) throws DAOException;

    List<T> findAll() throws DAOException;

    default boolean deleteHelper(int id, String query, ConnectionPool pool) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        int deleteResult;
        try {
            con = pool.takeConnection();
            ps = con.prepareStatement(query);
            ps.setInt(1, id);
            deleteResult = ps.executeUpdate();
        } catch (SQLException ex) {
            throw new DAOException(ex);
        } catch (ConnectionPoolException ex) {
            throw new DAOException(ex);
        } finally {
            pool.closeConnection(con, ps);
        }
        return deleteResult == 1 || deleteResult == 0;
    }
}
