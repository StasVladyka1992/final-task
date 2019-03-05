package by.vladyka.epam.controller.util;

import by.vladyka.epam.entity.User;

import java.util.HashMap;
import java.util.Map;

import static by.vladyka.epam.controller.util.JSPNavigation.*;

/**
 * Created by Vladyka Stas
 * on 01.03.2019 at 0:46
 **/
public final class UserNavigationManager {
    public static final Map<Character, String> authorization_scenarios = new HashMap<>();
    public static final Map<Character, String> remedy_searching_scenarios = new HashMap<>();
    static {
        authorization_scenarios.put('c', GO_TO_CLIENT_MAIN);
        authorization_scenarios.put('d', GO_TO_DOCTOR_MAIN);
        authorization_scenarios.put('p', GO_TO_PHARMACIST_MAIN);

        remedy_searching_scenarios.put('c', REMEDY_CLIENT);
        remedy_searching_scenarios.put('p', REMEDY_ADMINISTRATION);
    }


}
