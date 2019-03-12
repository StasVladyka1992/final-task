package by.vladyka.epam.service;

import by.vladyka.epam.entity.Remedy;
import by.vladyka.epam.entity.RemedySearchingResult;
import by.vladyka.epam.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by Vladyka Stas
 * on 26.02.2019 at 1:47
 **/
public interface RemedyService extends AbstractService<Remedy> {
   RemedySearchingResult findFromStartPosition(String name, int start, int offset) throws ServiceException;
   boolean update(int id, String name, String description, double price, boolean receiptRequired) throws ServiceException;
   boolean add(String name, String description, double price, boolean receiptRequired) throws ServiceException;
}
