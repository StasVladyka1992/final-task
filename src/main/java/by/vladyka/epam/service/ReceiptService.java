package by.vladyka.epam.service;

import by.vladyka.epam.dto.EntitySearchingResult;
import by.vladyka.epam.entity.Receipt;
import by.vladyka.epam.service.exception.ServiceException;

import java.util.Date;
import java.util.List;

/**
 * An interface that declare activities with {@link Receipt}
 *
 * @author Stas Vladyka
 * @version 1.0
 **/
public interface ReceiptService<T extends Receipt> {

    /**
     * Rejects client's prescription application
     *
     * @param id       prescription application id
     * @param doctorId doctor id
     * @param message  doctor's decision comment
     * @param status   new application status value. {@code REJECTED} status is assigned
     * @return result of application rejection, true - application is rejected, false otherwise
     * @throws ServiceException if there was exception  during interaction with data source
     * @see Receipt.Status
     */
    boolean rejectApplication(int id, int doctorId, String message, Receipt.Status status)
            throws ServiceException;

    /**
     * Finds unhandled prescription applications for pharmacist
     *
     * @return unhandled receipts
     * @throws ServiceException if there was exception  during interaction with data source
     * @see Receipt
     */
    List<T> findUnhandledApplications() throws ServiceException;

    /**
     * Finds unhandled prescription applications for client
     *
     * @param userId client id
     * @param start  pagination parameter that indicates start position from
     *               which searching of unhandled receipt will start
     * @param offset pagination parameter that indicates number of unhandled
     *               receipts per page
     * @return unhandled receipts and number of pages to show them
     * @throws ServiceException if there was exception  during interaction with data source
     * @see Receipt
     * @see EntitySearchingResult
     */
    EntitySearchingResult<T> findClientUnhandledApplications(int userId, int start, int offset)
            throws ServiceException;

    /**
     * Finds client written prescriptions
     *
     * @param userId client id
     * @param start  pagination parameter that indicates start position from
     *               which searching of written prescriptions will start
     * @param offset pagination parameter that indicates number of written
     *               prescriptions per page
     * @return written prescriptions and number of pages to show them
     * @throws ServiceException if there was exception during interaction with data source
     * @see Receipt
     * @see EntitySearchingResult
     */
    EntitySearchingResult<T> findClientWrittenPrescriptions(int userId, int start, int offset)
            throws ServiceException;

    /**
     * Finds client rejected applications
     *
     * @param userId client id
     * @param start  pagination parameter that indicates start position from
     *               which searching of rejected applications will start
     * @param offset pagination parameter that indicates number of rejected
     *               applications per page
     * @return rejected applications and number of pages to show them
     * @throws ServiceException if there was exception during interaction with data source
     * @see Receipt
     * @see EntitySearchingResult
     */
    EntitySearchingResult<T> findClientRejectedApplications(int userId, int start, int offset)
            throws ServiceException;

    /**
     * Finds all rejected applications by current doctor
     *
     * @param doctorId doctor id
     * @param start    pagination parameter that indicates start position from
     *                 which searching of rejected applications will start
     * @param offset   pagination parameter that indicates number of rejected
     *                 applications per page
     * @return rejected applications and number of pages to show them
     * @throws ServiceException if there was exception during interaction with data source
     * @see Receipt
     * @see EntitySearchingResult
     */
    EntitySearchingResult<T> findRejectedApplications(int doctorId, int start, int offset)
            throws ServiceException;

    /**
     * Finds all written prescriptions by current doctor
     *
     * @param doctorId doctor id
     * @param start    pagination parameter that indicates start position from
     *                 which searching of written prescriptions will start
     * @param offset   pagination parameter that indicates number of written
     *                 prescriptions per page
     * @return written prescriptions and number of pages to show them
     * @throws ServiceException if there was exception during interaction with data source
     * @see Receipt
     * @see EntitySearchingResult
     */
    EntitySearchingResult<T> findWrittenPrescriptions(int doctorId, int start, int offset)
            throws ServiceException;

    /**
     * Creates new appliance for prescription
     *
     * @param clientId client id
     * @param remedyId remedy id
     * @return result of a creating new appliance. true - application is created, false otherwise
     * @throws ServiceException if there was exception during interaction with data source
     */
    boolean createAppliance(int clientId, int remedyId) throws ServiceException;

    /**
     * Creates new valid prescription
     *
     * @param id               prescription id
     * @param doctorId         doctor id
     * @param expireDate       prescription expire date
     * @param prescriptionDate writing prescription date
     * @param message          doctor comment
     * @param status           prescription status. {@code APPROVE} status is assigned
     * @return result of a creating new prescription. true - application is created, false otherwise
     * @throws ServiceException if there was exception during interaction with data source
     * @see Date
     * @see Receipt.Status
     */
    boolean createPrescription(int id, int doctorId, Date expireDate, Date prescriptionDate, String message,
                               Receipt.Status status) throws ServiceException;
}
