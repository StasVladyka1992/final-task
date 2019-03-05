package by.vladyka.epam.controller.util;

/**
 * Created by Vladyka Stas
 * on 28.02.2019 at 23:08
 **/
public final class JSPNavigation {

    public static final String GO_TO_CLIENT_MAIN = "/secure?command=go_to_client_main";
    public static final String CLIENT_MAIN = "/WEB-INF/jsp/secure/client/client_main.jsp";
    public static final String GO_TO_REMEDY = "/secure?command=go_to_remedy";
    public static final String REMEDY_CLIENT = "/WEB-INF/jsp/secure/client/remedy.jsp";

    public static final String GO_TO_DOCTOR_MAIN = "/secure?command=go_to_doctor_main";

    public static final String GO_TO_PHARMACIST_MAIN = "/secure?command=go_to_pharmacist_main";
    public static final String PHARMACIST_MAIN = "/WEB-INF/jsp/secure/pharmacist/pharmacist_main.jsp";
    public static final String REMEDY_ADMINISTRATION = "/WEB-INF/jsp/secure/pharmacist/remedy_administration.jsp";

    public static final String GO_TO_REGISTRATION = "/index?command=go_to_registration&";
    public static final String REGISTRATION = "WEB-INF/jsp/user_registration.jsp";
    
    public static final String AUTHORIZATION = "WEB-INF/jsp/authorization.jsp";
    public static final String GO_TO_AUTHORIZATION = "/index?command=go_to_authorization";
    
    public static final String DEFAULT = "WEB-INF/jsp/default.jsp";
    
    public static final String REGISTRATION_RESULT= "/WEB-INF/jsp/secure/registration_result.jsp";
    public static final String GO_TO_REGISTRATION_RESULT = "/secure?command=user_info_after_registration";


}
