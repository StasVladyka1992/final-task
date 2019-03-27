package by.vladyka.epam.controller.util;

import by.vladyka.epam.dto.EntitySearchingResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static by.vladyka.epam.controller.util.ParameterName.PARAM_NAME_CURRENT_PAGE;
import static by.vladyka.epam.controller.util.ParameterName.PARAM_NAME_PAGES_NUMBER;

/**
 * Created by Vladyka Stas
 * on 01.03.2019 at 12:08
 **/
public final class Pagination {
    public static final int OFFSET = 5;

    public static int calculatePagesNumber(EntitySearchingResult entitySearchingResult) {
        int remediesNumber = entitySearchingResult.getFoundEntitiesNumber();
        int fullPages = remediesNumber / OFFSET;
        int notFullPages = remediesNumber % OFFSET;
        int result = 1;
        if (fullPages >= 1) {
            result = fullPages;
            if (notFullPages != 0) {
                result += 1;
            }
        }
        return result;
    }

    public static int calculateStartPosition(int currentPage) {
        return (currentPage - 1) * OFFSET;
    }

    public static String saveSearchingName(HttpServletRequest req, String parameterName) {
        String reqSearchingName = req.getParameter(parameterName);
        String sessionSearchingName = (String) req.getSession(true).getAttribute(parameterName);
        return reqSearchingName == null ? sessionSearchingName : reqSearchingName;
    }

    public static void setSessionPaginationParams(HttpSession session, int currentPage,
                                                  EntitySearchingResult searchingResult,
                                                  String paramListName) {
        session.setAttribute(PARAM_NAME_CURRENT_PAGE, currentPage);
        session.setAttribute(paramListName, searchingResult.getFoundEntities());
        session.setAttribute(PARAM_NAME_PAGES_NUMBER, calculatePagesNumber(searchingResult));
    }

    public static int getCurrentPage(HttpServletRequest req) {
        int currentPage = 1;
        String currentPageText = req.getParameter(PARAM_NAME_CURRENT_PAGE);
        if (currentPageText != null) {
            currentPage = Integer.parseInt(currentPageText);
        }
        return currentPage;
    }
}
