package by.vladyka.epam.dao;

import by.vladyka.epam.dao.exception.DAOException;
import by.vladyka.epam.entity.User;

import java.util.Map;

public interface UserDAO {
    User authentication(String login, String password) throws DAOException;

    boolean registration(Map<String, String> userData) throws DAOException;

    boolean updatePersonalInfo(Map<String, String> userData) throws DAOException;
}

