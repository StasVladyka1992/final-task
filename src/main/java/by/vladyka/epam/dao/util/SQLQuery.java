package by.vladyka.epam.dao.util;

/**
 * Created by Vladyka Stas
 * on 01.03.2019 at 11:54
 **/
public final class SQLQuery {
    // user's queries
    public static final String QUERY_CHECK_USER_EXISTANCE = "SELECT * FROM users WHERE email=?";
    public static final String QUERY_INSERT_USER = "INSERT INTO users (email, password, firstName, lastName, role, phone)" +
            " VALUES (?,?,?,?,?,?)";
    public static final String QUERY_CHECK_MAIL_USAGE = "SELECT COUNT(email) FROM users WHERE email=?";
    
    //remedy's queries
    public static final String QUERY_FIND_REMEDY = "SELECT * FROM remedies WHERE remedyName LIKE? LIMIT ?,?";
    public static final String QUERY_COUNT_SIMULAR_REMEDIES = "SELECT COUNT(name) FROM remedies WHERE remedyName LIKE?";
}
