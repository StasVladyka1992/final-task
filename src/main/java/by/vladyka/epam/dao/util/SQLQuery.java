package by.vladyka.epam.dao.util;

/**
 * Created by Vladyka Stas
 * on 01.03.2019 at 11:54
 **/
public final class SQLQuery {
    // user's queries
    public static final String QUERY_CHECK_USER_EXISTENCE = "SELECT * FROM users WHERE email=?";
    public static final String QUERY_INSERT_USER = "INSERT INTO users (email, password, firstName, lastName, role, phone)" +
            " VALUES (?,?,?,?,?,?)";
    public static final String QUERY_UPDATE_USER = "UPDATE users SET email=?, firstName=?, lastName=?, phone=? WHERE id=?";
    public static final String QUERY_CHECK_MAIL_USAGE = "SELECT COUNT(email) FROM users WHERE email=?";

    //remedy's queries
    public static final String QUERY_FIND_REMEDY_BY_ID = "SELECT * FROM remedies WHERE id=?";
    public static final String QUERY_COUNT_SIMILAR_REMEDIES = "SELECT COUNT(name) FROM remedies WHERE name LIKE";
    public static final String QUERY_DELETE_REMEDY = "DELETE FROM remedies WHERE id=?";
    public static final String QUERY_ADD_REMEDY = "INSERT INTO remedies (name, description, price, receiptRequired) VALUES (?,?,?,?)";
    public static final String QUERY_UPDATE_REMEDY = "UPDATE remedies SET name=?, description=?, price=?, receiptRequired=? WHERE id=";

    //storage's queries
    public static final String QUERY_FIND_REMEDIES_BY_NAME = "SELECT r.id, r.name, r.price, r.receiptRequired, r.description, s.remedyLeft, s.id FROM remedies AS r LEFT JOIN storage AS s on r.id=s.remedyId WHERE name LIKE? LIMIT ?,?";
    public static final String QUERY_ADD_REMEDY_TO_STORAGE = "INSERT INTO storage (remedyLeft, remedyId) values (?,?)";
    public static final String QUERY_GET_FULL_STORAGE_POSITION = "SELECT r.id, r.name, r.price, r.receiptRequired, r.description, s.remedyLeft, s.id FROM remedies AS r LEFT JOIN storage AS s on r.id=s.remedyId WHERE r.id=?";
    public static final String QUERY_DELETE_STORAGE_POSITION = "DELETE FROM storage WHERE id=?";
    public static final String QUERY_UPDATE_STORAGE_POSITION = "UPDATE storage SET remedyLeft=? WHERE remedyId=?";

    //receipt's queries
    public static final String QUERY_CREATE_RECEIPT = "INSERT INTO RECEIPTS (clientId, remedyId) VALUES (?,?)";
    public static final String QUERY_FIND_RECEIPT_BY_CLIENT_ID_AND_REMEDY_ID = "SELECT expireDate FROM receipts WHERE clientId=? and remedyId=?";
    public static final String QUERY_COUNT_UNHANDLED_APPLICATIONS = "SELECT COUNT(id) FROM RECEIPTS WHERE status='NONE'";
    public static final String QUERY_FIND_UNHANDLED_APPLICATIONS = "SELECT rec.id, rec.status, u.firstName, u.lastName, u.id, u.email, rem.name, rem.description FROM receipts AS rec LEFT JOIN remedies AS rem ON rec.remedyId = rem.id LEFT JOIN users AS u ON rec.clientId = u.id WHERE rec.prescriptionDate IS NULL AND rec.status='NONE' LIMIT ?,?";
    public static final String QUERY_COUNT_CLIENT_UNHANDLED_APPLICATIONS = "SELECT COUNT(id) FROM receipts WHERE status='NONE' and clientId=";
    public static final String QUERY_FIND_CLIENT_UNHANDLED_APPLICATIONS = "SELECT rec.id, rec.status, rem.name FROM receipts AS rec LEFT JOIN remedies AS rem ON rec.remedyId = rem.id LEFT JOIN users AS u ON rec.clientId = u.id WHERE rec.status='NONE' AND u.id=? LIMIT ?,?";
    public static final String QUERY_COUNT_CLIENT_WRITTEN_PRESCRIPTIONS = "SELECT COUNT(id) FROM receipts WHERE status='APPROVED' and clientId=";
    public static final String QUERY_FIND_CLIENT_WRITTEN_PRESCRIPTIONS = "SELECT rec.id, rec.status, rem.name, rec.expireDate FROM receipts AS rec LEFT JOIN remedies AS rem ON rec.remedyId = rem.id LEFT JOIN users AS u ON rec.clientId = u.id WHERE rec.status='APPROVED' AND u.id=? LIMIT ?,?";
    public static final String QUERY_COUNT_CLIENT_REJECTED_APPLICATIONS = "SELECT COUNT(id) FROM receipts WHERE status='REJECTED' and clientId=";
    public static final String QUERY_FIND_CLIENT_REJECTED_APPLICATIONS = "SELECT rec.id, rec.status, rem.name, rec.prescriptionDate, rec.message FROM receipts AS rec LEFT JOIN remedies AS rem ON rec.remedyId = rem.id LEFT JOIN users AS u ON rec.clientId = u.id WHERE rec.status='REJECTED' AND u.id=? LIMIT ?,?";


    public static final String QUERY_WRITE_PRESCRIPTION = "UPDATE receipts SET doctorId=?, expireDate=?, prescriptionDate=?, message=?, status=? WHERE id=?";
    public static final String QUERY_REJECT = "UPDATE receipts SET doctorId=?, message=?, status=?, prescriptionDate=? WHERE id=?";
    public static final String QUERY_COUNT_REJECTED_APPLICATIONS = "SELECT COUNT(id) FROM receipts WHERE doctorId=? and status='REJECTED'";
    public static final String QUERY_COUNT_WRITTEN_RECEIPTS = "SELECT COUNT(id) FROM receipts WHERE doctorId=? and status='APPROVED'";
    public static final String QUERY_FIND_REJECTED_APPLICATIONS = "SELECT rec.id, rec.status, rec.prescriptionDate, rec.expireDate, rec.message, u.firstName, u.lastName, u.email, rem.name FROM receipts AS rec LEFT JOIN remedies AS rem ON rec.remedyId = rem.id LEFT JOIN users AS u ON rec.clientId = u.id WHERE rec.doctorId=? AND rec.status='REJECTED' LIMIT ?,?";
    public static final String QUERY_FIND_WRITTEN_RECEIPTS = "SELECT rec.id, rec.status, rec.prescriptionDate, rec.expireDate, rec.message, u.firstName, u.lastName, u.email, rem.name FROM receipts AS rec LEFT JOIN remedies AS rem ON rec.remedyId = rem.id LEFT JOIN users AS u ON rec.clientId = u.id WHERE rec.doctorId=? AND rec.status='APPROVED' LIMIT ?,?";

    //client order's queries
    public static final String QUERY_CREATE_CLIENT_ORDER = "INSERT INTO client_orders (createdOn, clientId, sum) VALUES (NOW(),?,?)";
    public static final String QUERY_COUNT_UNHANDLED_CLIENT_ORDERS = "SELECT COUNT(id) FROM CLIENT_ORDERS WHERE status='UNHANDLED'";
    public static final String QUERY_GET_LAST_CLIENT_ORDER_ID = "SELECT MAX(id) FROM client_orders";
    public static final String QUERY_FIND_UNHANDLED_CLIENT_ORDERS = "SELECT c.id, c.createdOn, c.status, u.id FROM client_orders c LEFT JOIN users u on c.clientId = u.id WHERE status = 'UNHANDLED' LIMIT ?,?";
    public static final String QUERY_FIND_CLIENT_ORDER_BY_ID = "SELECT rem.name, rem.description, rem.receiptRequired, rem_or.quantity, client_or.createdOn, client_or.sum, rec.status, rec.id FROM client_orders client_or INNER JOIN remedy_orders rem_or ON client_or.id = rem_or.clientOrderId LEFT JOIN remedies rem ON rem_or.remedyId = rem.id LEFT JOIN receipts rec ON rec.remedyId = rem_or.remedyId WHERE client_or.id = ?";
    public static final String QUERY_SET_RECEIPTS_TO_CLIENT_ORDER = "UPDATE remedy_orders as rem_or SET rem_or.receiptId = (SELECT rec.id from receipts as rec where rec.clientId = ? and rem_or.remedyId = rec.remedyId) WHERE rem_or.clientOrderId = ?";
    public static final String QUERY_FIND_STORAGE_QUANTITY_FOR_REMEDY_ORDERS = "SELECT r.id, s.remedyLeft FROM remedy_orders r LEFT JOIN storage s ON r.remedyId=s.remedyId WHERE  clientOrderId=?";
    //remedy_order queries
    public static final String QUERY_CREATE_REMEDY_ORDER = "INSERT INTO remedy_orders (remedyId, quantity, clientOrderId) VALUES (";
}
