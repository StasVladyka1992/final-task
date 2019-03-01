package by.vladyka.epam.service;

import by.vladyka.epam.entity.Remedy;
import by.vladyka.epam.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by Vladyka Stas
 * on 26.02.2019 at 1:47
 **/
public interface RemedyService {
   List<Remedy> find(String name, int start, int offset) throws ServiceException;
   boolean update(Map<String, String> parameters) throws ServiceException;
   boolean add(Map<String, String> parameters) throws ServiceException;
   boolean delete(Map<String, String> parameters) throws ServiceException;
}
