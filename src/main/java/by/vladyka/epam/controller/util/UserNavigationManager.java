package by.vladyka.epam.controller.util;

import by.vladyka.epam.entity.User;
import by.vladyka.epam.service.validator.AbstractValidator;

import java.util.HashMap;
import java.util.Map;

import static by.vladyka.epam.controller.util.JSPNavigation.*;
import static by.vladyka.epam.entity.User.UserRole.*;


/**
 * Created by Vladyka Stas
 * on 01.03.2019 at 0:46
 **/
public final class UserNavigationManager {
    public static final Map<User.UserRole, String> authorization_scenarios = new HashMap<>();
    public static final Map<User.UserRole, String> remedy_searching_scenarios = new HashMap<>();

    static {
        authorization_scenarios.put(CLIENT, CLIENT_MAIN);
        authorization_scenarios.put(DOCTOR, DOCTOR_MAIN);
        authorization_scenarios.put(PHARMACIST, PHARMACIST_MAIN);

        remedy_searching_scenarios.put(CLIENT, REMEDY_CLIENT);
        remedy_searching_scenarios.put(PHARMACIST, REMEDY_ADMINISTRATION);
    }
}
