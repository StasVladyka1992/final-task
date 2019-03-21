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
//    public static final String QUERY_UPDATE_USER = "UPDATE users SET email=?, password=?, firstName=?, lastName=?, phone=? WHERE id=?";
    public static final String QUERY_CHECK_MAIL_USAGE = "SELECT COUNT(email) FROM users WHERE email=?";

    //remedy's queries
    public static final String QUERY_FIND_REMEDY_BY_ID = "SELECT * FROM remedies WHERE id=?";
    public static final String QUERY_COUNT_SIMULAR_REMEDIES = "SELECT COUNT(name) FROM remedies WHERE name LIKE?";
    public static final String QUERY_DELETE_REMEDY = "DELETE FROM remedies WHERE id=?";
    public static final String QUERY_ADD_REMEDY = "INSERT INTO remedies (name, description, price, receiptRequired) VALUES (?,?,?,?)";
    public static final String QUERY_UPDATE_REMEDY = "UPDATE remedies SET name=?, description=?, price=?, receiptRequired=? WHERE id=?";

    //storage's queries
    public static final String QUERY_FIND_REMEDIES_BY_NAME = "SELECT r.id, r.name, r.price, r.receiptRequired, r.description, s.remedyLeft, s.id FROM remedies AS r LEFT JOIN storage AS s on r.id=s.remedyId WHERE name LIKE? LIMIT ?,?";
    public static final String QUERY_ADD_REMEDY_TO_STORAGE = "INSERT INTO storage (remedyLeft, remedyId) values (?,?)";
    public static final String QUERY_GET_FULL_STORAGE_POSITION = "SELECT r.id, r.name, r.price, r.receiptRequired, r.description, s.remedyLeft, s.id FROM remedies AS r LEFT JOIN storage AS s on r.id=s.remedyId WHERE r.id=?";
    public static final String QUERY_DELETE_STORAGE_POSITION = "DELETE FROM storage WHERE id=?";
    public static final String QUERY_UPDATE_STORAGE_POSITION = "UPDATE storage SET remedyLeft=? WHERE remedyId=?";

    //receipt's queries
    public static final String QUERY_CREATE_RECEIPT = "INSERT INTO RECEIPTS (clientId, remedyId) VALUES (?,?)";
    public static final String QUERY_FIND_RECEIPT_BY_CLIENT_ID_AND_REMEDY_ID = "SELECT expireDate FROM receipts WHERE clientId=? and remedyId=?";
    public static final String  QUERY_COUNT_UNHANDLED_RECEIPTS = "SELECT COUNT(id) FROM RECEIPTS WHERE expireDate IS NULL and status='NONE'";
    public static final String QUERY_FIND_UNHANDLED_RECEIPTS = "SELECT rec.id, rec.status, u.firstName, u.lastName, u.id, u.email, rem.name, rem.description FROM receipts AS rec LEFT JOIN remedies AS rem ON rec.remedyId = rem.id LEFT JOIN users AS u ON rec.clientId = u.id WHERE rec.prescriptionDate IS NULL AND rec.status='NONE' LIMIT ?,?";
    public static final String QUERY_WRITE_PRESCRIPTION = "UPDATE receipts SET doctorId=?, expireDate=?, prescriptionDate=?, message=?, status=? WHERE id=?";
    public static final String QUERY_REJECT = "UPDATE receipts SET doctorId=?, message=?, status=?, prescriptionDate=? WHERE id=?";
    public static final String QUERY_COUNT_REJECTED_APPLICATIONS = "SELECT COUNT(id) FROM receipts WHERE doctorId=? and status='REJECTED'";
    public static final String QUERY_COUNT_WRITTEN_RECEIPTS = "SELECT COUNT(id) FROM receipts WHERE doctorId=? and status='APPROVED'";
    public static final String QUERY_FIND_REJECTED_APPLICATIONS = "SELECT rec.id, rec.status, rec.prescriptionDate, rec.message, u.firstName, u.lastName, u.email, rem.name FROM receipts AS rec LEFT JOIN remedies AS rem ON rec.remedyId = rem.id LEFT JOIN users AS u ON rec.clientId = u.id WHERE rec.doctorId=? AND rec.status='REJECTED' LIMIT ?,?";
    public static final String QUERY_FIND_WRITTEN_RECEIPTS = "SELECT rec.id, rec.status, rec.prescriptionDate, rec.message, u.firstName, u.lastName, u.email, rem.name FROM receipts AS rec LEFT JOIN remedies AS rem ON rec.remedyId = rem.id LEFT JOIN users AS u ON rec.clientId = u.id WHERE rec.doctorId=? AND rec.status='APPROVED' LIMIT ?,?";

    //client order's queries
    public static final String QUERY_CREATE_CLIENT_ORDER = "INSERT INTO client_orders (createdOn, clientId) VALUES (?,?)";
    public static final String QUERY_LAST_INSERT_ID = "SELECT LAST_INSERT_ID()";

    //remedy_order queries
}
