package by.vladyka.epam.service;

import by.vladyka.epam.entity.Receipt;
import by.vladyka.epam.service.exception.ServiceException;
import by.vladyka.epam.dto.EntitySearchingResult;

/**
 * Created by Vladyka Stas
 * on 15.03.2019 at 11:45
 **/
public interface ReceiptService extends AbstractService<Receipt> {
    boolean rejectApplication(int id, int doctorId, String message, Receipt.Status status) throws ServiceException;

    EntitySearchingResult<Receipt> findUnhandledReceipts(int start, int offset) throws ServiceException;

    EntitySearchingResult<Receipt> findRejectedApplications(int doctorId, int start, int offset) throws ServiceException;
    EntitySearchingResult<Receipt> findWrittenPrescriptions(int doctorId, int start, int offset) throws ServiceException;

    boolean createAppliance(int clientId, int remedyId) throws ServiceException;

    boolean createReceipt(int id, int doctorId, java.sql.Date expireDate, java.sql.Date prescriptionDate, String message, Receipt.Status status) throws ServiceException;
}
