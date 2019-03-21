package by.vladyka.epam.service.impl;

import by.vladyka.epam.dao.DAOProvider;
import by.vladyka.epam.dao.exception.DAOException;
import by.vladyka.epam.dao.impl.SQLReceiptDAO;
import by.vladyka.epam.dto.EntitySearchingResult;
import by.vladyka.epam.entity.Receipt;
import by.vladyka.epam.service.ReceiptService;
import by.vladyka.epam.service.exception.ServiceException;
import by.vladyka.epam.service.validator.impl.ReceiptValidator;

import java.util.List;

import static by.vladyka.epam.service.validator.util.IncorrectDataMessage.RECEIPT_EXIST;

/**
 * Created by Vladyka Stas
 * on 15.03.2019 at 11:52
 **/
public class ReceiptServiceImpl implements ReceiptService {
    private ReceiptValidator validator = new ReceiptValidator();

    @Override
    public EntitySearchingResult<Receipt> findUnhandledReceipts(int start, int offset) throws ServiceException {
        SQLReceiptDAO receiptDAO = (SQLReceiptDAO) DAOProvider.getInstance().getSQLReceiptDAO();
        EntitySearchingResult<Receipt> receipts;
        try {
            receipts = receiptDAO.findUnhandledReceipts(start, offset);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return receipts;
    }

    @Override
    public EntitySearchingResult<Receipt> findRejectedApplications(int doctorId, int start, int offset)
            throws ServiceException {
        boolean validationResult = validator.checkDoctorIdAndSetMessage(doctorId);
        if (!validationResult) {
            return null;
        }
        SQLReceiptDAO sqlReceiptDAO = (SQLReceiptDAO) DAOProvider.getInstance().getSQLReceiptDAO();
        EntitySearchingResult<Receipt> applications;
        try {
            applications = sqlReceiptDAO.findRejectedApplications(doctorId, start, offset);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return applications;
    }

    @Override
    public EntitySearchingResult<Receipt> findWrittenPrescriptions(int doctorId, int start, int offset)
            throws ServiceException {
        boolean validationResult = validator.checkDoctorIdAndSetMessage(doctorId);
        if (!validationResult) {
            return null;
        }
        EntitySearchingResult<Receipt> applications;
        SQLReceiptDAO sqlReceiptDAO = (SQLReceiptDAO) DAOProvider.getInstance().getSQLReceiptDAO();

        try {
            applications = sqlReceiptDAO.findWrittenPrescriptions(doctorId, start, offset);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return applications;
    }

    @Override
    public boolean rejectApplication(int id, int doctorId, String message, Receipt.Status status) throws ServiceException {
        boolean validationResult = validator.checkRejectionDataAndSetMessage(id, doctorId, message);
        if (!validationResult) {
            return false;
        }
        SQLReceiptDAO sqlReceiptDAO = (SQLReceiptDAO) DAOProvider.getInstance().getSQLReceiptDAO();
        boolean isUpdateSuccessfull;
        try {
            isUpdateSuccessfull = sqlReceiptDAO.reject(id, doctorId, message, status);

        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
        return isUpdateSuccessfull;
    }

    @Override
    public boolean createAppliance(int clientId, int remedyId) throws ServiceException {
        boolean validationResult = validator.checkClientAddingDataAndSetMessage(clientId, remedyId);
        if (!validationResult) {
            return false;
        }
        DAOProvider provider = DAOProvider.getInstance();
        SQLReceiptDAO sqlReceiptDAO = (SQLReceiptDAO) provider.getSQLReceiptDAO();
        boolean isAddingSuccessfull;
        try {
            if (sqlReceiptDAO.isValidReceiptExist(clientId, remedyId)) {
                validator.addIncorrectDataMessage(RECEIPT_EXIST);
                return false;
            }
            isAddingSuccessfull = provider.getSQLReceiptDAO().createAppliance(clientId, remedyId);
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
        return isAddingSuccessfull;
    }

    @Override
    public boolean createReceipt(int id, int doctorId, java.sql.Date expireDate, java.sql.Date prescriptionDate, String message,
                                 Receipt.Status status) throws ServiceException {
        boolean validationResult = validator.checkDoctorAddingDataAndSetMessage(id, doctorId, expireDate, prescriptionDate,
                message, status);
        if (!validationResult) {
            return false;
        }
        boolean isUpdatingSuccessfull;
        SQLReceiptDAO sqlReceiptDAO = (SQLReceiptDAO) DAOProvider.getInstance().getSQLReceiptDAO();
        try {
            isUpdatingSuccessfull = sqlReceiptDAO.createReceipt(id, doctorId, expireDate, prescriptionDate, message, status);
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
        return isUpdatingSuccessfull;
    }

    @Override
    public boolean delete(int id) throws ServiceException {
        return false;
    }

    @Override
    public Receipt findById(int id) throws ServiceException {
        return null;
    }

    @Override
    public List<Receipt> findAll() throws ServiceException {
        return null;
    }

    public ReceiptValidator getValidator() {
        return validator;
    }

    public void setValidator(ReceiptValidator validator) {
        this.validator = validator;
    }


}
