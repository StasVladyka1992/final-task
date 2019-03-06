package by.vladyka.epam.dao;

import by.vladyka.epam.dao.impl.SQLRemedyDAO;
import by.vladyka.epam.dao.impl.SQLUserDAO;

public class DAOProvider {
    private static final DAOProvider instance = new DAOProvider();
    private final UserDAO sqlUserDao = new SQLUserDAO();
    private final RemedyDAO sqlRemedyDao = new SQLRemedyDAO();

    private DAOProvider() {}

    public static DAOProvider getInstance(){
        return instance;
    }

    public UserDAO getSQLUserDAO(){
        return sqlUserDao;
    }
    public RemedyDAO getSQLRemedyDAO(){return sqlRemedyDao;}

}
