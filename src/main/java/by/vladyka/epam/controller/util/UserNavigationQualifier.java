package by.vladyka.epam.controller.util;

import java.util.HashMap;
import java.util.Map;

import static by.vladyka.epam.controller.util.JSPNavigation.*;

/**
 * Created by Vladyka Stas
 * on 01.03.2019 at 0:46
 **/
public final class UserNavigationQualifier {
    public static final Map<Character, String> scenarios = new HashMap<>();
    static{
        scenarios.put('c', GO_TO_CLIENT_MAIN);
        scenarios.put('d', GO_TO_DOCTOR_MAIN);
        scenarios.put('p', GO_TO_PHARMACIST_MAIN);
    }
}
