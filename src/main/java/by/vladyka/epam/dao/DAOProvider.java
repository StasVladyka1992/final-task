package by.vladyka.epam.dao;

import by.vladyka.epam.dao.impl.*;

public final class DAOProvider {
    private static final DAOProvider instance = new DAOProvider();
    private final UserDAO sqlUserDao = new SQLUserDAO();
    private final RemedyDAO sqlRemedyDao = new SQLRemedyDAO();
    private final StorageDAO sqlStorageDao = new SQLStorageDAO();
    private final ReceiptDAO sqlReceiptDao = new SQLReceiptDAO();
    private final ClientOrderDAO sqlClientOrderDao = new SQLClientOrderDAO();
    private final RemedyOrderDAO sqlRemedyOrderDao = new SQLRemedyOrderDAO();

    public static DAOProvider getInstance() {
        return instance;
    }

    public UserDAO getSQLUserDAO() {
        return sqlUserDao;
    }

    public RemedyDAO getSQLRemedyDAO() {
        return sqlRemedyDao;
    }

    public StorageDAO getSQLStorageDAO() {
        return sqlStorageDao;
    }

    public ReceiptDAO getSQLReceiptDAO() {
        return sqlReceiptDao;
    }

    public ClientOrderDAO getSQLClientOrderDAO() {
        return sqlClientOrderDao;
    }

    public RemedyOrderDAO getSQLRemedyOrderDAO() {
        return sqlRemedyOrderDao;
    }
}
