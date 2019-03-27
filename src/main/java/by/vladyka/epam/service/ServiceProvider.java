package by.vladyka.epam.service;

import by.vladyka.epam.service.impl.*;

/**
 *
 */
public class ServiceProvider {
    private static final ServiceProvider instance = new ServiceProvider();
    private final UserServiceImpl userService = new UserServiceImpl();
    private final RemedyServiceImpl remedyService = new RemedyServiceImpl();
    private final StorageServiceImpl storageService = new StorageServiceImpl();
    private final ReceiptServiceImpl receiptService = new ReceiptServiceImpl();
    private final ClientOrderServiceImpl clientOrderService = new ClientOrderServiceImpl();
    private final RemedyOrderServiceImpl remedyOrderService = new RemedyOrderServiceImpl();

    public ReceiptServiceImpl getReceiptService() {
        return receiptService;
    }

    public ClientOrderServiceImpl getClientOrderService() {
        return clientOrderService;
    }

    public RemedyOrderServiceImpl getRemedyOrderService() {
        return remedyOrderService;
    }

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
