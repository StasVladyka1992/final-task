package by.vladyka.epam.dao.util;

import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Created by Vladyka Stas
 * on 11.02.2019 at 1:23
 **/
public abstract class SQLConnectionHelper {

    protected static final Properties props = new Properties();
    protected static final String DRIVER;
    protected static final String URL;
    protected static final String USER;
    protected static final String PASSWORD;

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

        props.put("user", USER);
        props.put("password", PASSWORD);
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            // TODO log, не оборачивать в другие искл, вывести страницу с ошибкой. Прекратить работу?
        }
    }

}


