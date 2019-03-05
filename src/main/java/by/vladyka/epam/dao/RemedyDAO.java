package by.vladyka.epam.dao;

import by.vladyka.epam.dao.exception.DAOException;
import by.vladyka.epam.entity.Remedy;
import by.vladyka.epam.entity.RemedySearchingResult;

import java.util.List;
import java.util.Map;

/**
 * Created by Vladyka Stas
 * on 26.02.2019 at 1:59
 **/
public interface RemedyDAO {
    public RemedySearchingResult findRemedy(String name, int start, int offset) throws DAOException;
    public boolean updateRemedy(Map<String, String> parameters) throws DAOException;
    public boolean addRemedy(Map<String, String> parameters) throws DAOException;
    public boolean deleteRemedy(Map<String, String> parameters) throws DAOException;
}
