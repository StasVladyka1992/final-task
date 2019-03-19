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
    public static final String AUTHORIZATION = "/WEB-INF/jsp/authorization.jsp";
    public static final String PERSONAL_INFO = "/WEB-INF/jsp/secure/personal_info.jsp";
    public static final String GO_TO_AUTHORIZED_MAIN = "/secure?command=go_to_authorized_user_main_page";
    public static final String GO_TO_REGISTRATION = "/index?command=go_to_registration&";
    public static final String GO_TO_AUTHORIZATION = "/index?command=go_to_authorization&";
    public static final String GO_TO_REGISTRATION_RESULT = "/secure?command=user_info_after_registration";
    public static final String GO_TO_PERSONAL_INFO = "/secure?command=go_to_personal_info&";


    //client
    public static final String CLIENT_MAIN = "/WEB-INF/jsp/secure/client/client_main.jsp";
    public static final String REMEDY_CLIENT = "/WEB-INF/jsp/secure/client/remedy_searching.jsp";
    public static final String GO_TO_REMEDY = "/secure?command=go_to_remedy";
    public static final String GO_TO_CLIENT_SEARCHING_PAGE = "/secure?command=go_to_client_searching_page&";

    //doctor
    public static final String DOCTOR_MAIN = "/WEB-INF/jsp/secure/doctor/doctor_main.jsp";
    public static final String PRESCRIPTION_APPLICATION = "/WEB-INF/jsp/secure/doctor/prescription_application.jsp";
    public static final String REJECTION_LIST = "/WEB-INF/jsp/secure/doctor/rejection_list.jsp";
    public static final String PRESCRIPTION_LIST = "/WEB-INF/jsp/secure/doctor/prescription_list.jsp";
    public static final String GO_TO_PRESCRIPTION_APPLICATION = "/secure?command=go_to_prescription_application&";
    public static final String SHOW_REJECTED_APPLICATIONS = "/secure?command=show_rejected_applications&";
    public static final String GO_TO_REJECTION_LIST = "/secure?command=go_to_rejection_list&";
    public static final String GO_TO_PRESCRIPTION_LIST = "/secure?command=go_to_prescription_list&";

    //pharmacist
    public static final String PHARMACIST_MAIN = "/WEB-INF/jsp/secure/pharmacist/pharmacist_main.jsp";
    public static final String REMEDY_ADMINISTRATION = "/WEB-INF/jsp/secure/pharmacist/remedy_administration.jsp?";
    public static final String UPDATE_REMEDY = "/WEB-INF/jsp/secure/pharmacist/remedy_update.jsp";
    public static final String GO_TO_UPDATE_REMEDY = "/secure?command=go_to_update_remedy&";
    public static final String GO_TO_REMEDY_ADMINISTRATION = "/secure?command=go_to_remedy_administration&";

    public static String formNextUrl(boolean commandResult, AbstractValidator validator, String paramName,
                                     String paramValue, String url) {
        if (commandResult) {
            return url + paramName + '=' + paramValue;
        }
        String incorrectMessages = validator.getIncorrectDataMessages().toString();
        return url + incorrectMessages;
    }
}
