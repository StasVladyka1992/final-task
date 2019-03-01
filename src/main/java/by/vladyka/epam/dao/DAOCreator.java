package by.vladyka.epam.dao;

import by.vladyka.epam.dao.impl.SQLRemedyDAO;
import by.vladyka.epam.dao.impl.SQLUserDAO;

public class DAOCreator {
    private static final DAOCreator instance = new DAOCreator();
    private final UserDAO sqlUserDao = new SQLUserDAO();
    private final RemedyDAO sqlRemedyDao = new SQLRemedyDAO();

    private DAOCreator() {
    }

    public static DAOCreator getInstance(){
        return instance;
    }

    public UserDAO getSQLUserDAO(){
        return sqlUserDao;
    }
    public RemedyDAO getSQLRemedyDAO(){return sqlRemedyDao;}

}
