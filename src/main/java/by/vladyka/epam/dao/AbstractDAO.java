package by.vladyka.epam.dao;

import by.vladyka.epam.dao.util.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Vladyka Stas
 * on 06.03.2019 at 23:41
 **/
public abstract class AbstractDAO {
    protected Logger logger = LogManager.getLogger(AbstractDAO.class);

    public void closeConnection(Connection con, Statement st) {
        try {
            if (st != null) {
                st.close();
            }
        } catch (SQLException ex) {
            logger.error("Statement wasn't closed", ex);
        }
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {
            logger.error("Connection wasn't returned to the pool", ex);
        }
    }

    public void closeConnection(Connection con, Statement st, ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException ex) {
            logger.error("ResultSet wasn't closed", ex);
        }
        try {
            if (st != null) {
                st.close();
            }
        } catch (SQLException ex) {
            logger.error("Statement wasn't closed", ex);
        }
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {
            logger.error("Connection wasn't returned to the pool", ex);
        }
    }
}
