package by.vladyka.epam.dao;

import by.vladyka.epam.dao.exception.DAOException;
import by.vladyka.epam.entity.Receipt;
import by.vladyka.epam.service.exception.ServiceException;
import by.vladyka.epam.tdo.EntitySearchingResult;

/**
 * Created by Vladyka Stas
 * on 12.03.2019 at 11:15
 **/
public interface ReceiptDAO extends AbstractDAO<Receipt> {
    boolean createAppliance(int clientId, int remedyId) throws DAOException;

    boolean createReceipt(int id, int doctorId, java.sql.Date expireDate, java.sql.Date prescriptionDate, String message,
                          Receipt.Status status) throws DAOException;

    EntitySearchingResult<Receipt> findUnhandledReceipts(int start, int offset) throws DAOException;

    EntitySearchingResult<Receipt> findRejectedApplications(int doctorId, int start, int offset) throws DAOException;
    EntitySearchingResult<Receipt> findWrittenPrescriptions(int doctorId, int start, int offset) throws DAOException;

    boolean reject(int id, int doctorId, String message, Receipt.Status status)
            throws DAOException;
}
