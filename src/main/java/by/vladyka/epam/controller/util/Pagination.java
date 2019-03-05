package by.vladyka.epam.controller.util;

import by.vladyka.epam.entity.RemedySearchingResult;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Vladyka Stas
 * on 01.03.2019 at 12:08
 **/
public class Pagination {
    public static final int OFFSET = 5;

    public static int calculatePagesNumber(RemedySearchingResult remedySearchingResult) {
        int remediesNumber = remedySearchingResult.getFoundRemediesNumber();
        int fullPages = remediesNumber / OFFSET;
        int notFullPages = remediesNumber % OFFSET;
        int result;
        if (notFullPages == 0) {
            if (fullPages > 1) {
                result = fullPages;
            } else {
                result = 1;
            }
        } else {
            if (fullPages > 1) {
                result = fullPages + 1;
            } else {
                result = 1;
            }
        }
        return result;
    }

    public static int calculateStartPosition(int currrentPage) {
        if (currrentPage == 1) {
            return 0;
        } else {
            return (currrentPage - 1) * OFFSET;
        }
    }

    public static String saveSearchingName(HttpServletRequest req, String parameterName) {
        String reqSearchingName = req.getParameter(parameterName);
        String sessionSearchingName = (String) req.getSession(true).getAttribute(parameterName);
        return  reqSearchingName==null? sessionSearchingName:reqSearchingName;
    }

}
