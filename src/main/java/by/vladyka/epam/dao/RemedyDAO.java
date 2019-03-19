package by.vladyka.epam.dao;

import by.vladyka.epam.dao.exception.DAOException;
import by.vladyka.epam.entity.Remedy;

import java.util.Date;

/**
 * Created by Vladyka Stas
 * on 26.02.2019 at 1:59
 **/
public interface RemedyDAO extends AbstractDAO<Remedy> {
    boolean create(String name, String description, double price, boolean receiptRequired) throws DAOException;

    boolean update(int id, String name, String description, double price, boolean receiptRequired) throws DAOException;

}
