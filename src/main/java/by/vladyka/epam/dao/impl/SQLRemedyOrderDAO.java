package by.vladyka.epam.dao.impl;

import by.vladyka.epam.dao.RemedyOrderDAO;
import by.vladyka.epam.dao.exception.ConnectionPoolException;
import by.vladyka.epam.dao.exception.DAOException;
import by.vladyka.epam.dao.util.ConnectionPool;
import by.vladyka.epam.dto.OrderDto;
import by.vladyka.epam.entity.RemedyOrder;
import by.vladyka.epam.entity.Storage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static by.vladyka.epam.dao.util.SQLQuery.QUERY_CREATE_REMEDY_ORDER;

/**
 * Created by Vladyka Stas
 * on 21.03.2019 at 1:08
 **/
public class SQLRemedyOrderDAO implements RemedyOrderDAO {
    private static final ConnectionPool pool = ConnectionPool.getInstance();

    @Override
    public RemedyOrder findById(int id) throws DAOException {
        return null;
    }

    @Override
    public boolean deleteById(int id) throws DAOException {
        return false;
    }

    @Override
    public List<RemedyOrder> findAll() throws DAOException {
        return null;
    }

    @Override
    public boolean create(OrderDto orderDto, int clientOrderId) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        boolean result;
        try {
            con = pool.takeConnection();
            con.setAutoCommit(false);
            String[] commands = builtInsertCommands(orderDto, clientOrderId);
            ps = con.prepareStatement(commands[0]);
            if (commands.length > 1) {
                for (int i = 0; i < commands.length; i++) {
                    ps.addBatch(commands[i]);
                }
                int[] results = ps.executeBatch();
                for (int i = 0; i < commands.length; i++) {
                    if (results[i] != 1) {
                        throw new SQLException("Some of the remedy orders weren't created");
                    }
                }
                result=true;
            } else {
                int insertResult = ps.executeUpdate();
                result = insertResult == 1;
            }

            con.commit();
            return result;
        } catch (ConnectionPoolException | SQLException e) {
            try {
                con.rollback();
            } catch (SQLException e1) {
                throw new DAOException(e1);
            }
            throw new DAOException(e);
        } finally {
            pool.closeConnection(con, ps);
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
}
