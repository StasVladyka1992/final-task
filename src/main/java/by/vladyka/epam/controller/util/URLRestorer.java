package by.vladyka.epam.controller.util;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * Created by Vladyka Stas
 * on 16.02.2019 at 13:53
 **/
public final class URLRestorer {
    public static String restoreURL(HttpServletRequest req) {
        Enumeration<String> parameters = req.getParameterNames();
        String url = "";
        String paramName;
        String paramValue;
        while (parameters.hasMoreElements()) {
            paramName = parameters.nextElement();
            paramValue = req.getParameter(paramName);
            url += paramName + "=" + paramValue + "&";
        }
        url = req.getRequestURL() + "?" + url;
        return url;
    }

}
