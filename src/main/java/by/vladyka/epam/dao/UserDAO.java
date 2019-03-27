package by.vladyka.epam.dao;

import by.vladyka.epam.dao.exception.DAOException;
import by.vladyka.epam.entity.User;

public interface UserDAO extends AbstractDAO<User> {
    User authentication(String email, String password) throws DAOException;

    boolean registration(String email, String firstName, String lastName, String phone, String password,
                         User.UserRole role) throws DAOException;

    boolean update(int id, String email, String firstName, String lastName, String phone) throws DAOException;
}

