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
    public static final char CLIENT = 'c';
    public static final char DOCTOR = 'd';
    public static final char PHARMACIST = 'p';
    static {
        authorization_scenarios.put(CLIENT, GO_TO_CLIENT_MAIN);
        authorization_scenarios.put(DOCTOR, GO_TO_DOCTOR_MAIN);
        authorization_scenarios.put(PHARMACIST, GO_TO_PHARMACIST_MAIN);

        remedy_searching_scenarios.put(CLIENT, REMEDY_CLIENT);
        remedy_searching_scenarios.put(PHARMACIST, REMEDY_ADMINISTRATION);
    }


}
