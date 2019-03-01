package by.vladyka.epam.service;

import by.vladyka.epam.service.impl.RemedyServiceImpl;
import by.vladyka.epam.service.impl.UserServiceImpl;

/**
 * Created by Vladyka Stas
 * on 17.02.2019 at 14:50
 **/
public class ServiceCreator {
    private static final ServiceCreator instance = new ServiceCreator();
    private final UserServiceImpl userService = new UserServiceImpl();
    private final RemedyServiceImpl remedyService = new RemedyServiceImpl();

    public UserServiceImpl getUserService() {
        return userService;
    }

    public RemedyServiceImpl getRemedyService() {
        return remedyService;
    }

    public static ServiceCreator getInstance() {
        return instance;
    }
}
