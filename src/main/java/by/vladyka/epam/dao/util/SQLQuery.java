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
    public static final String QUERY_FIND_REMEDY = "SELECT * FROM remedies INNER JOIN storage ON remedies.id=storage.remedyId WHERE name LIKE? LIMIT ?,?";
    public static final String QUERY_FIND_REMEDY_BY_ID = "SELECT * FROM remedies WHERE id=?";
    public static final String QUERY_COUNT_SIMULAR_REMEDIES = "SELECT COUNT(name) FROM remedies WHERE name LIKE?";
    public static final String QUERY_DELETE_REMEDY = "DELETE FROM remedies WHERE id=?";
    public static final String QUERY_ADD_REMEDY = "INSERT INTO remedies (name, description, price, receiptRequired) VALUES (?,?,?,?)";
    public static final String QUERY_UPDATE_REMEDY = "UPDATE remedies SET name=?, description=?, price=?, receiptRequired=? WHERE id=?";

    //storage's queries
    public static final String QUERY_ADD_REMEDY_TO_STORAGE = "INSERT INTO storage (remedyId, remedyLeft) values (?,?)";
    public static final String QUERY_GET_FULL_STORAGE_POSITION = "SELECT r.id, r.name, r.price, r.receiptRequired, r.description, s.remedyLeft FROM remedies AS r LEFT JOIN storage AS s on r.id=s.remedyId WHERE r.id=?";
    public static final String QUERY_DELETE_STORAGE_POSITION = "DELETE FROM storage WHERE id=?";
}
