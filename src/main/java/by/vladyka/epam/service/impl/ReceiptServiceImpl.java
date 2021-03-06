package by.vladyka.epam.service.impl;

import by.vladyka.epam.dao.DAOProvider;
import by.vladyka.epam.dao.exception.DAOException;
import by.vladyka.epam.dao.impl.SQLReceiptDAO;
import by.vladyka.epam.dto.EntitySearchingResult;
import by.vladyka.epam.entity.Receipt;
import by.vladyka.epam.service.ReceiptService;
import by.vladyka.epam.service.exception.ServiceException;
import by.vladyka.epam.service.validator.impl.ReceiptValidator;

import java.util.Date;
import java.util.List;

import static by.vladyka.epam.service.validator.util.IncorrectDataMessage.RECEIPT_APPLICATION_EXIST;
import static by.vladyka.epam.service.validator.util.IncorrectDataMessage.RECEIPT_EXIST;

/**
 * Created by Vladyka Stas
 * on 15.03.2019 at 11:52
 **/
public class ReceiptServiceImpl implements ReceiptService {
    private ReceiptValidator validator = new ReceiptValidator();

    @Override
    public List<Receipt> findUnhandledApplications() throws ServiceException {
        validator.cleanBuffer();
        SQLReceiptDAO receiptDAO = (SQLReceiptDAO) DAOProvider.getInstance().getSQLReceiptDAO();
        List<Receipt> receipts;
        try {
            receipts = receiptDAO.findUnhandledApplications();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return receipts;
    }

    @Override
    public EntitySearchingResult<Receipt> findClientUnhandledApplications(int userId, int start, int offset) throws ServiceException {
        SQLReceiptDAO receiptDAO = (SQLReceiptDAO) DAOProvider.getInstance().getSQLReceiptDAO();
        EntitySearchingResult<Receipt> receipts;
        try {
            receipts = receiptDAO.findClientUnhandledApplications(userId, start, offset);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return receipts;
    }

    @Override
    public EntitySearchingResult<Receipt> findClientWrittenPrescriptions(int userId, int start, int offset) throws ServiceException {
        SQLReceiptDAO receiptDAO = (SQLReceiptDAO) DAOProvider.getInstance().getSQLReceiptDAO();
        EntitySearchingResult<Receipt> receipts;
        try {
            receipts = receiptDAO.findClientWrittenPrescriptions(userId, start, offset);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return receipts;
    }

    @Override
    public EntitySearchingResult<Receipt> findClientRejectedApplications(int userId, int start, int offset) throws ServiceException {
        SQLReceiptDAO receiptDAO = (SQLReceiptDAO) DAOProvider.getInstance().getSQLReceiptDAO();
        EntitySearchingResult<Receipt> receipts;
        try {
            receipts = receiptDAO.findClientRejectedApplications(userId, start, offset);
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
        validator.cleanBuffer();
        boolean validationResult = validator.checkRejectionDataAndSetMessage(id, doctorId, message);
        if (!validationResult) {
            return false;
        }
        SQLReceiptDAO sqlReceiptDAO = (SQLReceiptDAO) DAOProvider.getInstance().getSQLReceiptDAO();
        boolean isUpdateSuccessful;
        try {
            isUpdateSuccessful = sqlReceiptDAO.reject(id, doctorId, message, status);

        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
        return isUpdateSuccessful;
    }

    @Override
    public boolean createAppliance(int clientId, int remedyId) throws ServiceException {
        validator.cleanBuffer();
        boolean validationResult = validator.checkClientAddingDataAndSetMessage(clientId, remedyId);
        if (!validationResult) {
            return false;
        }
        DAOProvider provider = DAOProvider.getInstance();
        SQLReceiptDAO sqlReceiptDAO = (SQLReceiptDAO) provider.getSQLReceiptDAO();
        boolean isAddingSuccessful;
        try {
            int checkingResult = sqlReceiptDAO.isValidReceiptExist(clientId, remedyId);
            if (checkingResult == 1) {
                validator.addIncorrectDataMessage(RECEIPT_APPLICATION_EXIST);
                return false;
            } else if (checkingResult == 2) {
                validator.addIncorrectDataMessage(RECEIPT_EXIST);
                return false;
            }
            isAddingSuccessful = provider.getSQLReceiptDAO().createAppliance(clientId, remedyId);
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
        return isAddingSuccessful;
    }

    @Override
    public boolean createPrescription(int id, int doctorId, Date expireDate, Date prescriptionDate, String message,
                                      Receipt.Status status) throws ServiceException {
        validator.cleanBuffer();
        boolean validationResult = validator.checkDoctorAddingDataAndSetMessage(id, doctorId, expireDate,
                prescriptionDate, message);
        if (!validationResult) {
            return false;
        }
        boolean isUpdatingSuccessful;
        SQLReceiptDAO sqlReceiptDAO = (SQLReceiptDAO) DAOProvider.getInstance().getSQLReceiptDAO();
        try {
            isUpdatingSuccessful = sqlReceiptDAO.createReceipt(id, doctorId, expireDate, prescriptionDate, message,
                    status);
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
        return isUpdatingSuccessful;
    }

    public ReceiptValidator getValidator() {
        return validator;
    }

    public void setValidator(ReceiptValidator validator) {
        this.validator = validator;
    }

}
