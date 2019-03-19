package by.vladyka.epam.service;

import by.vladyka.epam.entity.Remedy;
import by.vladyka.epam.service.exception.ServiceException;

/**
 * Created by Vladyka Stas
 * on 26.02.2019 at 1:47
 **/
public interface RemedyService extends AbstractService<Remedy> {
    boolean update(int id, String name, String description, double price, boolean receiptRequired) throws ServiceException;

    boolean create(String name, String description, double price, boolean receiptRequired) throws ServiceException;
}
