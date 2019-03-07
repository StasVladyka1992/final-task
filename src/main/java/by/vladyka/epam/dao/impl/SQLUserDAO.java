package by.vladyka.epam.dao.impl;

import by.vladyka.epam.controller.util.ParameterName;
import by.vladyka.epam.dao.AbstractDAO;
import by.vladyka.epam.dao.UserDAO;
import by.vladyka.epam.dao.exception.ConnectionPoolException;
import by.vladyka.epam.dao.exception.DAOException;
import by.vladyka.epam.dao.util.ConnectionPool;
import by.vladyka.epam.entity.User;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.sql.*;
import java.util.Map;

import static by.vladyka.epam.controller.util.ParameterName.*;
import static by.vladyka.epam.dao.util.SQLQuery.*;

public class SQLUserDAO extends AbstractDAO implements UserDAO {
    private static final ConnectionPool pool = ConnectionPool.getInstance();

    @Override
    public User authentication(String email, String password) throws DAOException {
        User user = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = pool.takeConnection();
            preparedStatement = connection.prepareStatement(QUERY_CHECK_USER_EXISTANCE);
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                if (BCrypt.checkpw(password, resultSet.getString(ParameterName.PASSWORD))) {
                    user = createUser(resultSet);
                }
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            closeConnection(connection, preparedStatement, resultSet);
        }
        return user;
    }

    @Override
    public boolean updatePersonalInfo(Map<String, String> userData) throws DAOException {
        return false;
    }

    @Override
    public boolean registration(Map<String, String> userData) throws DAOException {
        if (isMailOccupied(userData.get(EMAIL))) {
            return false;
        }
        int insertionResult;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = pool.takeConnection();
            preparedStatement = connection.prepareStatement(QUERY_INSERT_USER);
            preparedStatement.setString(1, userData.get(EMAIL));
            preparedStatement.setString(2, BCrypt.hashpw(userData.get(ParameterName.PASSWORD), BCrypt.gensalt()));
            preparedStatement.setString(3, userData.get(FIRST_NAME));
            preparedStatement.setString(4, userData.get(LAST_NAME));
            preparedStatement.setString(5, userData.get(ROLE));
            preparedStatement.setString(6, userData.get(PHONE));
            insertionResult = preparedStatement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            closeConnection(connection, preparedStatement);
        }
        return insertionResult == 1;
    }

    private User createUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setIdUser(resultSet.getInt(ID_USER));
        user.setEmail(resultSet.getString(EMAIL));
        user.setFirstName(resultSet.getString(FIRST_NAME));
        user.setLastName(resultSet.getString(LAST_NAME));
        user.setRole(resultSet.getString(ROLE).charAt(0));
        user.setPhone(resultSet.getString(PHONE));
        return user;
    }

    private boolean isMailOccupied(String email) throws DAOException {
        int foundedEmailsNumber;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = pool.takeConnection();
            ps = con.prepareStatement(QUERY_CHECK_MAIL_USAGE);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                foundedEmailsNumber = rs.getInt(1);
                if (foundedEmailsNumber >= 1) {
                    return true;
                }
            }
        } catch (SQLException | ConnectionPoolException ex) {
            throw new DAOException(ex);
        } finally {
            closeConnection(con, ps, rs);
        }
        return false;
    }
}

