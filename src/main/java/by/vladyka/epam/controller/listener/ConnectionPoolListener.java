package by.vladyka.epam.controller.listener;

import by.vladyka.epam.dao.exception.ConnectionPoolException;
import by.vladyka.epam.dao.exception.InitializationPoolException;
import by.vladyka.epam.dao.util.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Created by Vladyka Stas
 * on 07.03.2019 at 2:03
 **/
@WebListener
public class ConnectionPoolListener implements ServletContextListener {
private static final ConnectionPool pool = ConnectionPool.getInstance();
private static final Logger logger  = LogManager.getLogger(ConnectionPoolListener.class);
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            pool.initPoolData();
        } catch (ConnectionPoolException e) {
            logger.error(e);
            throw new InitializationPoolException();
        }
    }
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        pool.disposeOfConnectionQueues();
    }
}
