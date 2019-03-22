package by.vladyka.epam.controller.util;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import static by.vladyka.epam.controller.util.ParameterName.*;

/**
 * Created by Vladyka Stas
 * on 10.03.2019 at 21:52
 **/
public class ParameterValue {
    public static final String PARAM_VALUE_OPERATION_RESULT_SUCCESS = "success";
    public static final String PARAM_VALUE_OPERATION_RESULT_FAIL = "fail";
    public static final String PARAM_VALUE_USER_NOT_FOUND = "user not found";
    public static final String PARAM_VALUE_REMEDY_NOT_EXIST = "remedy not exist ";
    public static final String PARAM_VALUE_APPLICATION_ACCEPTED = "applicationAccepted";
    public static final String PARAM_VALUE_PRESCRIPTION_WROTE = "prescriptionWritten";
    public static final String PARAM_VALUE_APPLICATION_REJECTED = "applicationRejected";
    public static final String PARAM_VALUE_GOOD_ALREADY_IN_BASKET = "goodAlreadyInBasket";
    public static final String PARAM_VALUE_REMEDY_DELETED = "remedy_deleted";
    public static final String PARAM_VALUE_QUANTITY_CHANGED = "quantity_changed";
    public static final String PARAM_VALUE_BASKET_CLEANED = "basket_cleaned";

    public static Map<String, String> getUserParams(HttpServletRequest req) {
        Map<String, String> userParams = new HashMap<>();

        return userParams;
    }

}
