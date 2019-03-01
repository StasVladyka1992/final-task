package by.vladyka.epam.dao;

import by.vladyka.epam.dao.exception.DAOException;
import by.vladyka.epam.entity.Remedy;

import java.util.List;
import java.util.Map;

/**
 * Created by Vladyka Stas
 * on 26.02.2019 at 1:59
 **/
public interface RemedyDAO {
    public List<Remedy> find(String name, int start, int offset) throws DAOException;
    public boolean update(Map<String, String> parameters) throws DAOException;
    public boolean add(Map<String, String> parameters) throws DAOException;
    public boolean delete(Map<String, String> parameters) throws DAOException;
}
