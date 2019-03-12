package by.vladyka.epam.service;

import by.vladyka.epam.entity.Storage;
import by.vladyka.epam.service.impl.RemedyServiceImpl;
import by.vladyka.epam.service.impl.StorageServiceImpl;
import by.vladyka.epam.service.impl.UserServiceImpl;

/**
 * Created by Vladyka Stas
 * on 17.02.2019 at 14:50
 **/
public class ServiceProvider {
    private static final ServiceProvider instance = new ServiceProvider();
    private final UserServiceImpl userService = new UserServiceImpl();
    private final RemedyServiceImpl remedyService = new RemedyServiceImpl();
    private final StorageServiceImpl storageService = new StorageServiceImpl();

    public UserServiceImpl getUserService() {
        return userService;
    }

    public RemedyServiceImpl getRemedyService() {
        return remedyService;
    }
    public StorageServiceImpl getStorageService() {
        return storageService;
    }



    public static ServiceProvider getInstance() {
        return instance;
    }
}
