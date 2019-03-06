package by.vladyka.epam.controller.util;

import by.vladyka.epam.entity.Remedy;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import static by.vladyka.epam.controller.util.ParameterName.*;

/**
 * Created by Vladyka Stas
 * on 21.02.2019 at 2:10
 **/


public final class ParameterDataExtractor {
//    public static Map<String, String> extractAllParameters(HttpServletRequest req) {
//        Map<String, String> parameters = new HashMap<>();
//        Enumeration<String> parameterNames = req.getParameterNames();
//        while (parameterNames.hasMoreElements()) {
//            String paramName = parameterNames.nextElement();
//            String paramValue = (req.getParameter(paramName));
//            parameters.put(paramName, paramValue);
//        }
//        return parameters;
//    }

    public static Map<String, String> extractUserRegistrationParameters(HttpServletRequest request) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put(EMAIL, request.getParameter(EMAIL));
        parameters.put(ROLE, request.getParameter(ROLE));
        parameters.put(FIRST_NAME, request.getParameter(FIRST_NAME));
        parameters.put(LAST_NAME, request.getParameter(LAST_NAME));
        parameters.put(PASSWORD, request.getParameter(PASSWORD));
        parameters.put(PHONE, request.getParameter(PHONE));
        return parameters;
    }

//    public static Map<String, String> extractRemedyAddingParameters(HttpServletRequest request){
//        Map<String, String> parameters = new HashMap<>();
//        Remedy remedy = (Remedy)request.getSession(false).getAttribute("remedy");
//        parameters.put("email", request.getParameter("name"));
//        parameters.put("role", request.getParameter("packing"));
//        parameters.put("firstName", request.getParameter("firstName"));
//        parameters.put("lastName", request.getParameter("lastName"));
//        parameters.put("password", request.getParameter("password"));
//        parameters.put("phone", request.getParameter("phone"));
//        return parameters;
//    }
}
