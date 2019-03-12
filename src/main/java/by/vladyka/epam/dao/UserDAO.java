package by.vladyka.epam.dao;

import by.vladyka.epam.dao.exception.DAOException;
import by.vladyka.epam.entity.User;
import by.vladyka.epam.entity.UserRole;

public interface UserDAO extends AbstractDAO<User> {
    User authentication(String email, String password) throws DAOException;

    boolean registration(String email, String firstName, String lastName, String password, String phone, UserRole role) throws DAOException;

    boolean update(String email, String firstName, String lastName, String password, String phone, UserRole role) throws DAOException;

}

