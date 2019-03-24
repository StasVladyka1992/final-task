package by.vladyka.epam.dao.impl;

import by.vladyka.epam.dao.ReceiptDAO;
import by.vladyka.epam.dao.exception.ConnectionPoolException;
import by.vladyka.epam.dao.exception.DAOException;
import by.vladyka.epam.dao.util.ConnectionPool;
import by.vladyka.epam.dto.EntitySearchingResult;
import by.vladyka.epam.entity.Receipt;
import by.vladyka.epam.entity.Remedy;
import by.vladyka.epam.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static by.vladyka.epam.dao.util.SQLDaoAssistant.getFoundEntitiesNumber;
import static by.vladyka.epam.dao.util.SQLDaoAssistant.setStartPositionAndOffset;
import static by.vladyka.epam.dao.util.SQLQuery.*;

/**
 * Created by Vladyka Stas
 * on 15.03.2019 at 12:51
 **/
public class SQLReceiptDAO implements ReceiptDAO {
    private static final ConnectionPool pool = ConnectionPool.getInstance();

    @Override
    public EntitySearchingResult<Receipt> findClientWrittenPrescriptions(int clientId, int start, int offset) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int writtenPrescriptionNumber;
        List<Receipt> receipts;
        try {
            String countCommand = QUERY_COUNT_CLIENT_WRITTEN_PRESCRIPTIONS + clientId;
            writtenPrescriptionNumber = getFoundEntitiesNumber(countCommand, pool);
            con = pool.takeConnection();
            ps = con.prepareStatement(QUERY_FIND_CLIENT_WRITTEN_PRESCRIPTIONS);
            setClientIdStartOffsetToStatement(ps, clientId, start, offset);
            receipts = new ArrayList<>();
            rs = ps.executeQuery();
            while (rs.next()) {
                Receipt receipt = new Receipt();
                receipt.setId(rs.getInt(1));
                receipt.setStatus(Receipt.Status.valueOf(rs.getString(2)));
                receipt.setExpireDate(new Date(rs.getTimestamp(4).getTime()));
                Remedy remedy = new Remedy();
                remedy.setName(rs.getString(3));

                receipt.setRemedy(remedy);
                receipts.add(receipt);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            pool.closeConnection(con, ps, rs);
        }
        EntitySearchingResult<Receipt> writtenPrescriptions = new EntitySearchingResult<>();
        writtenPrescriptions.setFoundEntities(receipts);
        writtenPrescriptions.setFoundEntitiesNumber(writtenPrescriptionNumber);
        return writtenPrescriptions;
    }

    @Override
    public EntitySearchingResult<Receipt> findClientRejectedApplications(int clientId, int start, int offset)
            throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int rejectedApplicationsNumber;
        List<Receipt> receipts;
        try {
            String countCommand = QUERY_COUNT_CLIENT_REJECTED_APPLICATIONS + clientId;
            rejectedApplicationsNumber = getFoundEntitiesNumber(countCommand, pool);
            con = pool.takeConnection();
            ps = con.prepareStatement(QUERY_FIND_CLIENT_REJECTED_APPLICATIONS);
            setClientIdStartOffsetToStatement(ps, clientId, start, offset);
            receipts = new ArrayList<>();
            rs = ps.executeQuery();
            while (rs.next()) {
                Receipt receipt = new Receipt();
                receipt.setId(rs.getInt(1));
                receipt.setStatus(Receipt.Status.valueOf(rs.getString(2)));
                receipt.setPrescriptionDate(new Date(rs.getTimestamp(4).getTime()));
                receipt.setMessage(rs.getString(5));
                Remedy remedy = new Remedy();
                remedy.setName(rs.getString(3));

                receipt.setRemedy(remedy);
                receipts.add(receipt);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            pool.closeConnection(con, ps, rs);
        }
        EntitySearchingResult<Receipt> rejectedApplications = new EntitySearchingResult<>();
        rejectedApplications.setFoundEntities(receipts);
        rejectedApplications.setFoundEntitiesNumber(rejectedApplicationsNumber);
        return rejectedApplications;
    }

    @Override
    public EntitySearchingResult<Receipt> findClientUnhandledApplications(int clientId, int start, int offset)
            throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int unhandledReceiptsNumber;
        List<Receipt> receipts;
        try {
            String countCommand = QUERY_COUNT_CLIENT_UNHANDLED_APPLICATIONS + clientId;
            unhandledReceiptsNumber = getFoundEntitiesNumber(countCommand, pool);
            con = pool.takeConnection();
            ps = con.prepareStatement(QUERY_FIND_CLIENT_UNHANDLED_APPLICATIONS);
            setClientIdStartOffsetToStatement(ps, clientId, start, offset);
            receipts = new ArrayList<>();
            rs = ps.executeQuery();
            while (rs.next()) {
                Receipt receipt = new Receipt();
                receipt.setId(rs.getInt(1));
                receipt.setStatus(Receipt.Status.valueOf(rs.getString(2)));

                Remedy remedy = new Remedy();
                remedy.setName(rs.getString(3));

                receipt.setRemedy(remedy);
                receipts.add(receipt);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            pool.closeConnection(con, ps, rs);
        }
        EntitySearchingResult<Receipt> unhandledReceipts = new EntitySearchingResult<>();
        unhandledReceipts.setFoundEntities(receipts);
        unhandledReceipts.setFoundEntitiesNumber(unhandledReceiptsNumber);
        return unhandledReceipts;

    }

    @Override
    public EntitySearchingResult<Receipt> findUnhandledApplications(int start, int offset) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int unhandledReceiptsNumber;
        List<Receipt> receipts;
        try {
            unhandledReceiptsNumber = getFoundEntitiesNumber(QUERY_COUNT_UNHANDLED_APPLICATIONS, pool);
            con = pool.takeConnection();
            ps = con.prepareStatement(QUERY_FIND_UNHANDLED_APPLICATIONS);
            setStartPositionAndOffset(ps, start, offset);
            receipts = new ArrayList<>();
            rs = ps.executeQuery();
            while (rs.next()) {
                Receipt receipt = new Receipt();
                receipt.setId(rs.getInt(1));
                receipt.setStatus(Receipt.Status.valueOf(rs.getString(2)));

                User client = new User();
                client.setFirstName(rs.getString(3));
                client.setLastName(rs.getString(4));
                client.setId(rs.getInt(5));
                client.setEmail(rs.getString(6));

                Remedy remedy = new Remedy();
                remedy.setName(rs.getString(7));
                remedy.setDescription(rs.getString(8));

                receipt.setRemedy(remedy);
                receipt.setClient(client);
                receipts.add(receipt);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            pool.closeConnection(con, ps, rs);
        }
        EntitySearchingResult<Receipt> unhandledReceipts = new EntitySearchingResult<>();
        unhandledReceipts.setFoundEntities(receipts);
        unhandledReceipts.setFoundEntitiesNumber(unhandledReceiptsNumber);
        return unhandledReceipts;
    }

    @Override
    public boolean createAppliance(int clientId, int remedyId) throws DAOException {
        boolean result = createAndSetClientReceiptParamToPreparedStatement(clientId, remedyId, QUERY_CREATE_RECEIPT);
        return result;
    }

    @Override
    public boolean createReceipt(int id, int doctorId, Date expireDate, Date prescriptionDate, String message,
                                 Receipt.Status status) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        int insertionResult;
        try {
            con = pool.takeConnection();
            ps = con.prepareStatement(QUERY_WRITE_PRESCRIPTION);
            ps.setInt(1, doctorId);
            ps.setTimestamp(2, new Timestamp(expireDate.getTime()));
            ps.setTimestamp(3, new Timestamp(prescriptionDate.getTime()));
            ps.setString(4, message);
            ps.setString(5, status.toString());
            ps.setInt(6, id);
            insertionResult = ps.executeUpdate();
        } catch (SQLException | ConnectionPoolException ex) {
            throw new DAOException(ex);
        } finally {
            pool.closeConnection(con, ps);
        }
        return insertionResult == 1;
    }

    public int isValidReceiptExist(int clientId, int remedyId) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = pool.takeConnection();
            ps = con.prepareStatement(QUERY_FIND_RECEIPT_BY_CLIENT_ID_AND_REMEDY_ID);
            setClientIdAndRemedyIdToStatement(ps, clientId, remedyId);
            rs = ps.executeQuery();
            if (rs.next()) {
                Date expireDate = rs.getDate(1);
                if (expireDate == null) {
                    return 1;
                } else if ((expireDate.getTime() - new Date().getTime()) >= 0) {
                    return 2;
                }
            }
        } catch (SQLException | ConnectionPoolException ex) {
            throw new DAOException(ex);
        } finally {
            pool.closeConnection(con, ps, rs);
        }
        return 0;
    }

    @Override
    public boolean reject(int id, int doctorId, String message, Receipt.Status status) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        int insertionResult;
        try {
            con = pool.takeConnection();
            ps = con.prepareStatement(QUERY_REJECT);
            ps.setInt(1, doctorId);
            ps.setString(2, message);
            ps.setString(3, status.toString());
            ps.setDate(4, new java.sql.Date(new Date().getTime()));
            ps.setInt(5, id);
            ps.executeUpdate();
            insertionResult = ps.executeUpdate();
        } catch (SQLException | ConnectionPoolException ex) {
            throw new DAOException(ex);
        } finally {
            pool.closeConnection(con, ps);
        }
        return insertionResult == 1;
    }

    @Override
    public EntitySearchingResult<Receipt> findRejectedApplications(int doctorId, int start, int offset)
            throws DAOException {
        return findHandledApplications(doctorId, start, offset, QUERY_COUNT_REJECTED_APPLICATIONS,
                QUERY_FIND_REJECTED_APPLICATIONS);
    }

    @Override
    public EntitySearchingResult<Receipt> findWrittenPrescriptions(int doctorId, int start, int offset)
            throws DAOException {
        return findHandledApplications(doctorId, start, offset, QUERY_COUNT_WRITTEN_RECEIPTS,
                QUERY_FIND_WRITTEN_RECEIPTS);
    }

    private EntitySearchingResult<Receipt> findHandledApplications(int doctorId, int start, int offset,
                                                                   String queryCountApplications,
                                                                   String queryFindApplications) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int writtenPrescriptionsNumber;
        List<Receipt> writtenPrescriptions;
        try {
            writtenPrescriptionsNumber = getFoundSpecialReceiptsNumber(queryCountApplications, doctorId);
            con = pool.takeConnection();
            ps = con.prepareStatement(queryFindApplications);
            ps.setInt(1, doctorId);
            //setStartPosition не подходит, т.к. у них позиция 2,3
            ps.setInt(2, start);
            ps.setInt(3, offset);
            rs = ps.executeQuery();
            writtenPrescriptions = new ArrayList<>();
            while (rs.next()) {
                Receipt application = new Receipt();
                application.setId(rs.getInt(1));
                application.setStatus(Receipt.Status.valueOf(rs.getString(2)));
                application.setPrescriptionDate(rs.getDate(3));
                if (rs.getTimestamp(4) == null) {
                    application.setExpireDate(null);
                } else {
                    application.setExpireDate(new Date(rs.getTimestamp(4).getTime()));
                }
                application.setMessage(rs.getString(5));

                User client = new User();
                client.setFirstName(rs.getString(6));
                client.setLastName(rs.getString(7));
                client.setEmail(rs.getString(8));

                Remedy remedy = new Remedy();
                remedy.setName(rs.getString(9));

                application.setRemedy(remedy);
                application.setClient(client);
                writtenPrescriptions.add(application);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            pool.closeConnection(con, ps, rs);
        }
        EntitySearchingResult<Receipt> result = new EntitySearchingResult<>();
        result.setFoundEntities(writtenPrescriptions);
        result.setFoundEntitiesNumber(writtenPrescriptionsNumber);
        return result;
    }

    private void setClientIdAndRemedyIdToStatement(PreparedStatement ps, int clientId, int remedyId) throws
            SQLException {
        ps.setInt(1, clientId);
        ps.setInt(2, remedyId);
    }

    private void setClientIdStartOffsetToStatement(PreparedStatement ps, int clientId, int start, int offset) throws SQLException {
        ps.setInt(1, clientId);
        ps.setInt(2, start);
        ps.setInt(3, offset);
    }

    private boolean createAndSetClientReceiptParamToPreparedStatement(int clientId, int remedyId, String query)
            throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        int insertionResult;
        try {
            con = pool.takeConnection();
            ps = con.prepareStatement(query);
            ps.setInt(2, remedyId);
            ps.setInt(1, clientId);
            insertionResult = ps.executeUpdate();
        } catch (SQLException | ConnectionPoolException ex) {
            throw new DAOException(ex);
        } finally {
            pool.closeConnection(con, ps);
        }
        return insertionResult == 1;
    }


    private int getFoundSpecialReceiptsNumber(String query, int id) throws DAOException {
        int foundReceiptsNumber = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = pool.takeConnection();
            ps = con.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                foundReceiptsNumber = rs.getInt(1);
            }
        } catch (SQLException | ConnectionPoolException ex) {
            throw new DAOException(ex);
        } finally {
            pool.closeConnection(con, ps, rs);
        }
        return foundReceiptsNumber;
    }

    @Override
    public Receipt findById(int id) throws DAOException {
        return null;
    }

    @Override
    public boolean deleteById(int id) throws DAOException {
        return false;
    }

    @Override
    public List<Receipt> findAll() throws DAOException {
        return null;
    }
}
