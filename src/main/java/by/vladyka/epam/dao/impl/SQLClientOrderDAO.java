package by.vladyka.epam.dao.impl;

import by.vladyka.epam.dao.ClientOrderDAO;
import by.vladyka.epam.dao.exception.ConnectionPoolException;
import by.vladyka.epam.dao.exception.DAOException;
import by.vladyka.epam.dao.util.ConnectionPool;
import by.vladyka.epam.dto.EntitySearchingResult;
import by.vladyka.epam.dto.OrderDto;
import by.vladyka.epam.dto.OrderDtoForPharmacist;
import by.vladyka.epam.entity.*;

import java.sql.*;
import java.util.Date;
import java.util.*;

import static by.vladyka.epam.dao.util.SQLDaoAssistant.getFoundEntitiesNumber;
import static by.vladyka.epam.dao.util.SQLQuery.*;

/**
 * Created by Vladyka Stas
 * on 21.03.2019 at 1:15
 **/
public class SQLClientOrderDAO implements ClientOrderDAO {
    private static final ConnectionPool pool = ConnectionPool.getInstance();

    @Override
    public EntitySearchingResult<ClientOrder> findUnhandledClientOrders(int clientId, int start, int offset)
            throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int unhandledClientOrdersNumber;
        EntitySearchingResult<ClientOrder> clientOrders;
        try {
            String query = QUERY_COUNT_UNHANDLED_CLIENT_ORDERS + clientId;
            unhandledClientOrdersNumber = getFoundEntitiesNumber(query, pool);
            con = pool.takeConnection();
            ps = con.prepareStatement(QUERY_FIND_UNHANDLED_CLIENT_ORDERS);
            rs = setParamsToFindOrderQueryAndExecute(ps, clientId, start, offset);
            clientOrders = new EntitySearchingResult<>();
            ClientOrder order;
            while (rs.next()) {
                order = new ClientOrder();
                order.setId(rs.getInt(1));
                order.setCreatedOn(new Date(rs.getTimestamp(2).getTime()));
                order.setStatus(ClientOrder.ClientOrderStatus.valueOf(rs.getString(3)));
                order.setSum(rs.getDouble(4));
                clientOrders.getFoundEntities().add(order);
            }
            clientOrders.setFoundEntitiesNumber(unhandledClientOrdersNumber);
        } catch (SQLException | ConnectionPoolException ex) {
            throw new DAOException(ex);
        } finally {
            pool.closeConnection(con, ps, rs);
        }
        return clientOrders;
    }

    @Override
    public EntitySearchingResult<ClientOrder> findHandledClientOrders(int clientId, int start, int offset)
            throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int handledClientOrdersNumber;
        EntitySearchingResult<ClientOrder> clientOrders;
        try {
            String query = QUERY_COUNT_HANDLED_CLIENT_ORDERS + clientId;
            handledClientOrdersNumber = getFoundEntitiesNumber(query, pool);
            con = pool.takeConnection();
            ps = con.prepareStatement(QUERY_FIND_HANDLED_CLIENT_ORDERS);
            setParamsToFindOrderQueryAndExecute(ps, clientId, start, offset);
            setParamsToFindOrderQueryAndExecute(ps, clientId, start, offset);
            rs = setParamsToFindOrderQueryAndExecute(ps, clientId, start, offset);
            clientOrders = new EntitySearchingResult<>();
            ClientOrder order;
            while (rs.next()) {
                order = new ClientOrder();
                getCreatedOnDate(rs, order);
                order.setStatus(ClientOrder.ClientOrderStatus.valueOf(rs.getString(4)));
                order.setSum(rs.getDouble(5));
                clientOrders.getFoundEntities().add(order);
            }
            clientOrders.setFoundEntitiesNumber(handledClientOrdersNumber);
        } catch (SQLException | ConnectionPoolException ex) {
            throw new DAOException(ex);
        } finally {
            pool.closeConnection(con, ps, rs);
        }
        return clientOrders;
    }

    private ResultSet setParamsToFindOrderQueryAndExecute(PreparedStatement ps, int clientId, int start, int offset)
            throws SQLException {
        ps.setInt(1, clientId);
        ps.setInt(2, start);
        ps.setInt(3, offset);
        return ps.executeQuery();
    }

    @Override
    public boolean confirmOrder(OrderDtoForPharmacist order) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        PreparedStatement ps3 = null;

        Map<RemedyOrder, Integer> remedyOrders = order.getRemedyOrders();
        int count = 0;
        try {
            con = pool.takeConnection();
            con.setAutoCommit(false);
            for (Map.Entry<RemedyOrder, Integer> pair :
                    remedyOrders.entrySet()) {
                if (count == 0) {
                    ps = con.prepareStatement(QUERY_REDUCE_STORAGE_QUANTITY);
                    ps.setInt(1, pair.getKey().getQuantity());
                    ps.setInt(2, pair.getKey().getRemedy().getId());
                    ps.addBatch();
                    count++;
                } else {
                    ps.setInt(1, pair.getKey().getQuantity());
                    ps.setInt(2, pair.getKey().getRemedy().getId());
                    ps.addBatch();
                }
            }
            int[] reduceStorageQuantityResults = ps.executeBatch();
            count = 0;
            for (int reduceStorageQuantityResult : reduceStorageQuantityResults) {
                if (reduceStorageQuantityResult <= 0) {
                    throw new SQLException("Not all storage quantities were updated after purchasing");
                }
            }
            ps2 = con.prepareStatement(QUERY_CHANGE_ORDER_STATUS_TO_EXECUTED);
            ps2.setInt(1, order.getId());
            int orderUpdateResult = ps2.executeUpdate();

            for (Map.Entry<RemedyOrder, Integer> pair :
                    remedyOrders.entrySet()) {
                if (count == 0) {
                    ps3 = con.prepareStatement(QUERY_MARKED_USED_RECEIPTS);
                    count = addNewBatch(ps3, count, pair);
                } else {
                    count = addNewBatch(ps3, count, pair);
                    ps3.addBatch();
                }
            }
            int[] changingReceiptsStatusResult = ps3.executeBatch();
            for (int i = 0; i < changingReceiptsStatusResult.length; i++) {
                if (changingReceiptsStatusResult[i] <= 0) {
                    throw new SQLException("Not all used receipt's statuses were changed");
                }
            }
            if (orderUpdateResult == 1) {
                con.commit();
                return true;
            }
        } catch (SQLException | ConnectionPoolException ex) {
            doRollback(con);
            throw new DAOException(ex);
        } finally {
            pool.closeStatement(ps);
            pool.closeStatement(ps2);
            pool.closeConnection(con, ps3);
        }
        return false;
    }

    private int addNewBatch(PreparedStatement ps3, int count, Map.Entry<RemedyOrder, Integer> pair)
            throws SQLException {
        Receipt receipt = pair.getKey().getReceipt();
        if (receipt != null) {
            ps3.setInt(1, receipt.getId());
            ps3.addBatch();
            count++;
        }
        return count;
    }

    @Override
    public boolean rejectOrder(int id) throws DAOException {
        return abstractUpdatePattern(id, QUERY_REJECT_ORDER, pool) == 1;
    }

    public OrderDtoForPharmacist findByIdForPharmacist(int id) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        OrderDtoForPharmacist order = null;
        try {
            con = pool.takeConnection();
            ps = con.prepareStatement(QUERY_FIND_ORDER_BY_ID_FOR_PHARMACIST);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            User user;
            while (rs.next()) {
                if (order == null) {
                    order = new OrderDtoForPharmacist();
                    order.setRemedyOrders(new HashMap<>());
                    user = new User();
                    user.setId(rs.getInt(1));
                    order.setId(id);
                    order.setClient(user);
                    order.setCreatedOn(rs.getTimestamp(2));
                    order.setSum(rs.getDouble(3));
                }
                Remedy remedy = new Remedy();
                remedy.setId(rs.getInt(4));
                remedy.setName(rs.getString(5));
                remedy.setDescription(rs.getString(6));
                remedy.setReceiptRequired(rs.getBoolean(7));

                RemedyOrder remedyOrder = new RemedyOrder();
                remedyOrder.setQuantity(rs.getInt(8));

                String receiptStatus = rs.getString(9);
                if (receiptStatus != null) {
                    Receipt receipt = new Receipt();
                    receipt.setId(rs.getInt(10));
                    receipt.setStatus(Receipt.Status.valueOf(receiptStatus));
                    remedyOrder.setReceipt(receipt);
                }
                int remedyLeft = rs.getInt(11);
                remedyOrder.setRemedy(remedy);
                order.getRemedyOrders().put(remedyOrder, remedyLeft);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            pool.closeConnection(con, ps, rs);
        }
        return order;
    }

    public List<ClientOrder> findUnhandledOrders() throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<ClientOrder> clientOrders;
        try {
            con = pool.takeConnection();
            ps = con.prepareStatement(QUERY_FIND_UNHANDLED_ORDERS);
            rs = ps.executeQuery();
            clientOrders = new ArrayList<>();
            ClientOrder order = null;
            while (rs.next()) {
                int clientOrderId = rs.getInt(1);
                if (order == null) {
                    order = buildClientOrder(rs);
                    order.setId(clientOrderId);
                    clientOrders.add(order);
                } else {
                    if (clientOrderId != order.getId()) {
                        order = buildClientOrder(rs);
                        order.setId(clientOrderId);
                        clientOrders.add(order);
                    } else {
                        RemedyOrder remedyOrder = buildRemedyOrder(rs);
                        order.getRemedyOrders().add(remedyOrder);
                    }
                }
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            pool.closeConnection(con, ps, rs);
        }
        return clientOrders;
    }

    private ClientOrder buildClientOrder(ResultSet rs) throws SQLException {
        ClientOrder order = new ClientOrder();
        order.setRemedyOrders(new ArrayList<>());
        order.setCreatedOn(new Date(rs.getTimestamp(2).getTime()));
        order.setStatus(ClientOrder.ClientOrderStatus.valueOf(rs.getString(3)));
        User user = new User();
        user.setId(rs.getInt(4));
        RemedyOrder remedyOrder = buildRemedyOrder(rs);
        order.getRemedyOrders().add(remedyOrder);
        order.setClient(user);
        return order;
    }

    private RemedyOrder buildRemedyOrder(ResultSet rs) throws SQLException {
        RemedyOrder remedyOrder = new RemedyOrder();
        remedyOrder.setId(rs.getInt(5));
        Remedy remedy = new Remedy();
        remedy.setId(rs.getInt(6));
        remedyOrder.setRemedy(remedy);
        remedyOrder.setQuantity(rs.getInt(7));
        return remedyOrder;
    }

    @Override
    public boolean create(int clientId, double sum, OrderDto orderDto) throws DAOException {
        Connection con = null;
        PreparedStatement ps0 = null;
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        try {
            con = pool.takeConnection();
            con.setAutoCommit(false);
            ps0 = con.prepareStatement(QUERY_CREATE_CLIENT_ORDER, Statement.RETURN_GENERATED_KEYS);
            ps0.setInt(1, clientId);
            ps0.setDouble(2, sum);
            if (ps0.executeUpdate()!=1){
                throw new SQLException("client order wasn't created");
            }
            rs = ps0.getGeneratedKeys();
            if (rs.next()) {
                int insertedId = rs.getInt(1);
                String[] commands = builtInsertCommands(orderDto, insertedId);
                ps1 = con.prepareStatement(commands[0]);
                if (commands.length >= 1) {
                    for (String command : commands) {
                        ps1.addBatch(command);
                    }
                    int[] results = ps1.executeBatch();
                    for (int i = 0; i < commands.length; i++) {
                        if (results[i] != 1) {
                            throw new SQLException("Some of the remedy orders weren't created");
                        }
                    }
                }
            }
            con.commit();
            return true;
        } catch (SQLException | ConnectionPoolException ex) {
            try {
                con.rollback();
            } catch (SQLException e) {
                throw new DAOException(e);
            }
            throw new DAOException(ex);
        } finally {
            pool.closeStatementAndResultSet(ps0, rs);
            pool.closeConnection(con, ps1);
        }
    }

    private String[] builtInsertCommands(OrderDto dto, int clientOrderId) {
        int commandsNumber = dto.getGoods().size();
        String[] commands = new String[commandsNumber];
        int count = 0;
        for (Map.Entry<Storage, Integer> pair :
                dto.getGoods().entrySet()) {
            int remedyId = pair.getKey().getRemedy().getId();
            int quantity = pair.getValue();
            String command = QUERY_CREATE_REMEDY_ORDER + remedyId + "," +
                    quantity + "," + clientOrderId + ")";
            commands[count] = command;
            count++;
        }
        return commands;
    }

    @Override
    public EntitySearchingResult<ClientOrder> findHandledOrders(int start, int offset) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        EntitySearchingResult<ClientOrder> clientOrders = new EntitySearchingResult<>();
        try {
            int foundEntitiesNumber = getFoundEntitiesNumber(QUERY_COUNT_HANDLED_ORDERS, pool);
            clientOrders.setFoundEntitiesNumber(foundEntitiesNumber);
            con = pool.takeConnection();
            ps = con.prepareStatement(QUERY_FIND_HANDLED_ORDERS);
            ps.setInt(1, start);
            ps.setInt(2, offset);
            rs = ps.executeQuery();
            while (rs.next()) {
                ClientOrder order = new ClientOrder();
                User user = new User();
                getCreatedOnDate(rs, order);
                user.setId(rs.getInt(4));
                order.setClient(user);
                order.setStatus(ClientOrder.ClientOrderStatus.valueOf(rs.getString(5)));
                order.setSum(rs.getDouble(6));
                clientOrders.getFoundEntities().add(order);
            }
        } catch (SQLException | ConnectionPoolException ex) {
            throw new DAOException(ex);
        } finally {
            pool.closeConnection(con, ps, rs);
        }

        return clientOrders;
    }

    private void getCreatedOnDate(ResultSet rs, ClientOrder order) throws SQLException {
        order.setId(rs.getInt(1));
        order.setCreatedOn(new Date(rs.getTimestamp(2).getTime()));
        Timestamp time = rs.getTimestamp(3);
        if (time != null) {
            order.setFinishedOn(new Date(time.getTime()));
        }
    }

}
