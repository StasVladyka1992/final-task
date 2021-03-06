package by.vladyka.epam.dao;

import by.vladyka.epam.dao.exception.DAOException;
import by.vladyka.epam.dto.EntitySearchingResult;
import by.vladyka.epam.entity.Storage;

/**
 * Created by Vladyka Stas
 * on 11.03.2019 at 13:02
 **/
public interface StorageDAO extends AbstractDAO<Storage> {
    EntitySearchingResult findFromStartPosition(String name, int start, int offset) throws DAOException;

    boolean create(int remedyId, int remedyLeft) throws DAOException;

    boolean update(int remedyId, int remedyLeft) throws DAOException;

    Storage findById(int id) throws DAOException;
}
