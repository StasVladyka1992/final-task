package by.vladyka.epam.dao.impl;

import by.vladyka.epam.dao.UserDAO;
import by.vladyka.epam.dao.exception.DAOException;
import by.vladyka.epam.dao.util.SQLConnectionHelper;
import by.vladyka.epam.entity.User;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.sql.*;
import java.util.Map;

import static by.vladyka.epam.dao.util.SQLQuery.*;

public class SQLUserDAO extends SQLConnectionHelper implements UserDAO {
    @Override
    public User authentication(String email, String password) throws DAOException {
        User user = null;
        try (Connection connection = DriverManager.getConnection(URL, props);
             PreparedStatement preparedStatement = connection.prepareStatement(QUERY_CHECK_USER_EXISTANCE)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                if (BCrypt.checkpw(password, resultSet.getString("password"))) {
                    user = createUser(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return user;
    }

    @Override
    public boolean updatePersonalInfo(Map<String, String> userData) throws DAOException {
        return false;
    }

    @Override
    public boolean registration(Map<String, String> userData) throws DAOException {
        if (isMailOccupied(userData.get("email"))) {
            return false;
        }
        int insertionResult;
        try (Connection connection = DriverManager.getConnection(URL, props);
             PreparedStatement preparedStatement = connection.prepareStatement(QUERY_INSERT_USER)) {
            preparedStatement.setString(1, userData.get("email"));
            preparedStatement.setString(2, BCrypt.hashpw(userData.get("password"), BCrypt.gensalt()));
            preparedStatement.setString(3, userData.get("firstName"));
            preparedStatement.setString(4, userData.get("lastName"));
            preparedStatement.setString(5, userData.get("role"));
            preparedStatement.setString(6, userData.get("phone"));
            insertionResult = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }

        return insertionResult == 1;
    }

    private User createUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setIdUser(resultSet.getInt("idUser"));
        user.setEmail(resultSet.getString("email"));
        user.setFirstName(resultSet.getString("firstName"));
        user.setLastName(resultSet.getString("lastName"));
        user.setRole(resultSet.getString("role").charAt(0));
        user.setPhone(resultSet.getString("phone"));
        return user;
    }

    private static boolean isMailOccupied(String email) throws DAOException {
        int foundedEmailsNumber;
        try (Connection connection = DriverManager.getConnection(URL, props);
             PreparedStatement statement = connection.prepareStatement(QUERY_CHECK_MAIL_USAGE)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                foundedEmailsNumber = resultSet.getInt(1);
                if (foundedEmailsNumber >= 1) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            throw new DAOException();
        }
        return false;
    }
}

