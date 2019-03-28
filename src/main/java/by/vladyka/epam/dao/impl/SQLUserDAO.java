package by.vladyka.epam.dao.impl;

import by.vladyka.epam.controller.util.ParameterName;
import by.vladyka.epam.dao.UserDAO;
import by.vladyka.epam.dao.exception.ConnectionPoolException;
import by.vladyka.epam.dao.exception.DAOException;
import by.vladyka.epam.dao.util.ConnectionPool;
import by.vladyka.epam.entity.User;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static by.vladyka.epam.dao.util.DBColumn.*;
import static by.vladyka.epam.dao.util.SQLQuery.*;

public class SQLUserDAO implements UserDAO {
    private static final ConnectionPool pool = ConnectionPool.getInstance();

    @Override
    public User authentication(String email, String password) throws DAOException {
        User user = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = pool.takeConnection();
            preparedStatement = connection.prepareStatement(QUERY_CHECK_USER_EXISTENCE);
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                if (BCrypt.checkpw(password, resultSet.getString(ParameterName.PARAM_NAME_PASSWORD))) {
                    user = createUser(resultSet);
                }
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            pool.closeConnection(connection, preparedStatement, resultSet);
        }
        return user;
    }

    @Override
    public boolean registration(String email, String firstName, String lastName, String phone, String password,
                                User.UserRole role) throws DAOException {
        if (isMailOccupied(email)) {
            return false;
        }
        int insertionResult;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = pool.takeConnection();
            preparedStatement = connection.prepareStatement(QUERY_INSERT_USER);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, BCrypt.hashpw(password, BCrypt.gensalt()));
            preparedStatement.setString(3, firstName);
            preparedStatement.setString(4, lastName);
            preparedStatement.setString(5, role.toString());
            preparedStatement.setString(6, phone);
            insertionResult = preparedStatement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            pool.closeConnection(connection, preparedStatement);
        }
        return insertionResult == 1;
    }

    @Override
    public boolean update(int id, String email, String firstName, String lastName, String phone)
            throws DAOException {
        int insertionResult;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = pool.takeConnection();
            preparedStatement = connection.prepareStatement(QUERY_UPDATE_USER);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, firstName);
            preparedStatement.setString(3, lastName);
            preparedStatement.setString(4, phone);
            preparedStatement.setInt(5, id);
            insertionResult = preparedStatement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            pool.closeConnection(connection, preparedStatement);
        }
        return insertionResult == 1;
    }

    private User createUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt(ID));
        user.setEmail(resultSet.getString(EMAIL));
        user.setFirstName(resultSet.getString(FIRST_NAME));
        user.setLastName(resultSet.getString(LAST_NAME));
        user.setRole(User.UserRole.valueOf(resultSet.getString(ROLE)));
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
            pool.closeConnection(con, ps, rs);
        }
        return false;
    }
}

