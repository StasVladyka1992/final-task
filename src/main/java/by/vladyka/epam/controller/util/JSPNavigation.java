package by.vladyka.epam.controller.util;

import by.vladyka.epam.service.validator.AbstractValidator;

/**
 * Created by Vladyka Stas
 * on 28.02.2019 at 23:08
 **/
public final class JSPNavigation {
    //common
    public static final String DEFAULT = "/WEB-INF/jsp/default.jsp";
    public static final String REGISTRATION_RESULT = "/WEB-INF/jsp/secure/registration_result.jsp";
    public static final String REGISTRATION = "/WEB-INF/jsp/user_registration.jsp";
    public static final String ERROR_PAGE = "error.jsp";
    public static final String AUTHORIZATION = "/WEB-INF/jsp/authorization.jsp";
    public static final String PERSONAL_INFO = "/WEB-INF/jsp/secure/personal_info.jsp";
    public static final String HANDLED_ORDER_LIST = "/WEB-INF/jsp/secure/pharmacist/handled_order_list.jsp";
    public static final String GO_TO_AUTHORIZED_MAIN = "/secure?command=go_to_authorized_user_main_page";
    public static final String GO_TO_REGISTRATION = "/index?command=go_to_registration&";
    public static final String GO_TO_AUTHORIZATION = "/index?command=go_to_authorization&";
    public static final String GO_TO_REGISTRATION_RESULT = "/secure?command=user_info_after_registration";
    public static final String GO_TO_PERSONAL_INFO = "/secure?command=go_to_personal_info&";
    public static final String GO_TO_HANDLED_ORDER_LIST = "/secure?command=go_to_handled_order_list&";
    //client
    public static final String CLIENT_MAIN = "/WEB-INF/jsp/secure/client/client_main.jsp";
    public static final String REMEDY_CLIENT = "/WEB-INF/jsp/secure/client/remedy_searching.jsp";
    public static final String BASKET = "/WEB-INF/jsp/secure/client/basket.jsp";
    public static final String REMEDY_INFO = "/WEB-INF/jsp/secure/client/remedy_info.jsp";
    public static final String ORDER_STATUS = "/WEB-INF/jsp/secure/client/order_status.jsp";
    public static final String PRESCRIPTION_APPLICATION_LIST = "/WEB-INF/jsp/secure/client/prescription_application_list.jsp";
    public static final String CLIENT_WRITTEN_PRESCRIPTION_LIST = "/WEB-INF/jsp/secure/client/client_written_prescription_list.jsp";
    public static final String CLIENT_REJECTED_APPLICATION_LIST = "/WEB-INF/jsp/secure/client/client_rejected_application_list.jsp";
    public static final String CLIENT_UNHANDLED_ORDER_LIST = "/WEB-INF/jsp/secure/client/client_unhandled_order_list.jsp";
    public static final String CLIENT_HANDLED_ORDER_LIST = "/WEB-INF/jsp/secure/client/client_handled_order_list.jsp";
    public static final String GO_TO_REMEDY = "/secure?command=go_to_remedy";
    public static final String SHOW_REMEDY_INFO = "/secure?command=show_remedy_info&";
    public static final String GO_TO_CLIENT_SEARCHING_PAGE = "/secure?command=go_to_client_searching_page&";
    public static final String GO_TO_BASKET = "/secure?command=go_to_basket&";
    public static final String GO_TO_ORDER_STATUS = "/secure?command=go_to_order_status&";
    public static final String GO_TO_PRESCRIPTION_APPLICATION_LIST = "/secure?command=go_to_prescription_application_list&";
    public static final String GO_TO_CLIENT_WRITTEN_PRESCRIPTION_LIST = "/secure?command=go_to_client_written_prescription_list&";
    public static final String GO_TO_CLIENT_REJECTED_APPLICATION_LIST = "/secure?command=go_to_client_rejected_application_list&";
    public static final String GO_TO_CLIENT_UNHANDLED_ORDER_LIST = "/secure?command=go_to_client_unhandled_order_list&";
    public static final String GO_TO_CLIENT_HANDLED_ORDER_LIST = "/secure?command=go_to_client_handled_order_list&";
    //doctor
    public static final String DOCTOR_MAIN = "/WEB-INF/jsp/secure/doctor/doctor_main.jsp";
    public static final String PRESCRIPTION_APPLICATION = "/WEB-INF/jsp/secure/doctor/prescription_application.jsp";
    public static final String REJECTION_LIST = "/WEB-INF/jsp/secure/doctor/rejection_list.jsp";
    public static final String PRESCRIPTION_LIST = "/WEB-INF/jsp/secure/doctor/prescription_list.jsp";
    public static final String GO_TO_PRESCRIPTION_APPLICATION = "/secure?command=go_to_prescription_application&";
    public static final String GO_TO_REJECTION_LIST = "/secure?command=go_to_rejection_list&";
    public static final String GO_TO_PRESCRIPTION_LIST = "/secure?command=go_to_prescription_list&";
    //pharmacist
    public static final String PHARMACIST_MAIN = "/WEB-INF/jsp/secure/pharmacist/pharmacist_main.jsp";
    public static final String REMEDY_ADMINISTRATION = "/WEB-INF/jsp/secure/pharmacist/remedy_administration.jsp";
    public static final String PURCHASE_ADMINISTRATION = "/WEB-INF/jsp/secure/pharmacist/purchase_administration.jsp";
    public static final String REMEDY_UPDATE = "/WEB-INF/jsp/secure/pharmacist/remedy_update.jsp";
    public static final String CLIENT_ORDER = "/WEB-INF/jsp/secure/pharmacist/client_order.jsp";
    public static final String GO_TO_REMEDY_ADMINISTRATION = "/secure?command=go_to_remedy_administration&";
    public static final String GO_TO_CLIENT_ORDER = "/secure?command=go_to_client_order&";
    public static final String SHOW_UNHANDLED_ORDER_LIST = "/secure?command=show_unhandled_order_list&";
    public static final String GO_TO_PURCHASE_ADMINISTRATION = "/secure?command=go_to_purchase_administration&";

    public static String formNextUrl(boolean commandResult, AbstractValidator validator, String paramName,
                                     String paramValue, String url) {
        if (commandResult) {
            return url + paramName + '=' + paramValue;
        } else {
            String incorrectMessages = validator.getIncorrectDataMessages().toString();
            return url + incorrectMessages;
        }
    }
}
