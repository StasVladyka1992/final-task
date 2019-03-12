package by.vladyka.epam.controller.util;

/**
 * Created by Vladyka Stas
 * on 28.02.2019 at 23:08
 **/
public final class JSPNavigation {
    //client
    public static final String CLIENT_MAIN = "/WEB-INF/jsp/secure/client/client_main.jsp";
    public static final String REMEDY_CLIENT = "/WEB-INF/jsp/secure/client/remedy.jsp";
    public static final String GO_TO_REMEDY = "/secure?command=go_to_remedy";

    //doctor
    public static final String DOCTOR_MAIN = "/WEB-INF/jsp/secure/doctor/doctor_main.jsp";


    //pharmacist
    public static final String PHARMACIST_MAIN = "/WEB-INF/jsp/secure/pharmacist/pharmacist_main.jsp";
    public static final String REMEDY_ADMINISTRATION = "/WEB-INF/jsp/secure/pharmacist/remedy_administration.jsp?";
    public static final String GO_TO_UPDATE_REMEDY = "/secure?command=go_to_update_remedy&";
    public static final String GO_TO_REMEDY_ADMINISTRATION = "/secure?command=go_to_remedy_administration&";
    public static final String UPDATE_REMEDY = "/WEB-INF/jsp/secure/pharmacist/remedy_update.jsp";

    //common
    public static final String FIND_REMEDY = "/secure?command=find_remedy&";
    public static final String GO_TO_AUTHORIZED_MAIN = "/secure?command=go_to_authorized_user_main_page";
    public static final String GO_TO_REGISTRATION = "/index?command=go_to_registration&";
    public static final String REGISTRATION = "WEB-INF/jsp/user_registration.jsp";
    public static final String AUTHORIZATION = "WEB-INF/jsp/authorization.jsp";
    public static final String GO_TO_AUTHORIZATION = "/index?command=go_to_authorization&";
    public static final String DEFAULT = "WEB-INF/jsp/default.jsp";
    public static final String REGISTRATION_RESULT = "/WEB-INF/jsp/secure/registration_result.jsp";
    public static final String GO_TO_REGISTRATION_RESULT = "/secure?command=user_info_after_registration";
}
