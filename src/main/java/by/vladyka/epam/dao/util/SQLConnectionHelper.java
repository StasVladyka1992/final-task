package by.vladyka.epam.dao.util;

import by.vladyka.epam.dao.exception.NoDBDriverException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Created by Vladyka Stas
 * on 11.02.2019 at 1:23
 **/
public abstract class SQLConnectionHelper {
    private static final Logger logger = LogManager.getLogger(SQLConnectionHelper.class);
    protected static final Properties PROPS = new Properties();
    protected static final String DRIVER;
    protected static final String URL;
    protected static final String USER;
    protected static final String PASSWORD;
    private static final String USER_STRING = "user";
    private static final String PASSWORD_STRING = "password";

    private static final String DB_DRIVER = "databaseDriver";
    private static final String DB_URL = "databaseURL";
    private static final String DB_USER = "databaseLogin";
    private static final String DB_PASSWORD = "databasePassword";

    static {
        ResourceBundle jdbcProperties = ResourceBundle.getBundle("database");
        DRIVER = jdbcProperties.getString(DB_DRIVER);
        URL = jdbcProperties.getString(DB_URL);
        USER = jdbcProperties.getString(DB_USER);
        PASSWORD = jdbcProperties.getString(DB_PASSWORD);

        PROPS.put(USER_STRING, USER);
        PROPS.put(PASSWORD_STRING, PASSWORD);
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            logger.error(e);
            throw new NoDBDriverException("Can't find database driver", e);
        }
    }

}


