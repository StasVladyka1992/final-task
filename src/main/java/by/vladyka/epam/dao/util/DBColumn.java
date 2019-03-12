package by.vladyka.epam.dao.util;

/**
 * Created by Vladyka Stas
 * on 09.03.2019 at 21:55
 **/
public final class DBColumn {
    //common
    public static final String ID = "id";
    public static final String REMEDY_ID = "remedyId";
    public static final String CLIENT_ID = "clientId";

    //remedies
    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";
    public static final String PRICE = "price";
    public static final String RECEIPT_REQUIRED = "receiptRequired";

    //users
    public static final String EMAIL = "email";
    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";
    public static final String PASSWORD = "password";
    public static final String PHONE = "phone";
    public static final String ROLE = "role";

    //receipts
    public static final String EXPIRE_DATE = "expireDate";
    public static final String PRESCRIPTION_DATE = "prescriptionDate";
    public static final String DOCTOR_ID = "doctorId";
    public static final String MESSAGE = "message";
    public static final String USED = "used";

    //client order
    public static final String CREATED_ON = "createdOn";
    public static final String FINISHED_ON = "finishedOn";
    public static final String REMEDY_ORDER_ID = "remedyOrderId";

    //remedy order
    public static final String QUANTITY_ORDER = "quantity";
    public static final String RECEIPT_ID = "receiptId";
    public static final String ORDER_ID = "orderId";

    //storage
    public static final String REMEDY_LEFT = "remedyLeft";

}
