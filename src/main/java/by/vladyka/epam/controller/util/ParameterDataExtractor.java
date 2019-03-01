package by.vladyka.epam.controller.util;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vladyka Stas
 * on 21.02.2019 at 2:10
 **/
public final class ParameterDataExtractor {
    public static Map<String, String> extractAllParameters(HttpServletRequest req) {
        Map<String, String> parameters = new HashMap<>();
        Enumeration<String> parameterNames = req.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            String paramValue = (req.getParameter(paramName));
            parameters.put(paramName, paramValue);
        }
        return parameters;
    }

    public static Map<String, String> extractUserRegistrationParameters(HttpServletRequest request) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("email", request.getParameter("email"));
        parameters.put("role", request.getParameter("role"));
        parameters.put("firstName", request.getParameter("firstName"));
        parameters.put("lastName", request.getParameter("lastName"));
        parameters.put("password", request.getParameter("password"));
        parameters.put("phone", request.getParameter("phone"));
        return parameters;
    }
}
