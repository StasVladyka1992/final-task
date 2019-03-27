package by.vladyka.epam.dao.exception;

/**
 * ConnectionPoolException signals thant error occurred in the
 * {@link by.vladyka.epam.dao.util.ConnectionPool}
 *
 * @author Stas Vladyka
 * @version 1.0
 * @see Exception
 **/
public class ConnectionPoolException extends Exception {

    /**
     * Constructs an {@code ConnectionPoolException} with {@code null}
     * as its error detail message.
     */
    public ConnectionPoolException() {
    }

    /**
     * Constructs an {@code ConnectionPoolException} with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval
     *                by the {@link #getMessage()} method)
     */
    public ConnectionPoolException(String message) {
        super(message);
    }

    /**
     * Constructs an {@code ConnectionPoolException} with the specified detail message
     * and cause.
     *
     * <p> Note that the detail message associated with {@code cause} is
     * <i>not</i> automatically incorporated into this exception's detail
     * message.
     *
     * @param message The detail message (which is saved for later retrieval
     *                by the {@link #getMessage()} method)
     * @param cause   The cause (which is saved for later retrieval by the
     *                {@link #getCause()} method).  (A null value is permitted,
     *                and indicates that the cause is nonexistent or unknown.)
     */
    public ConnectionPoolException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs an {@code ConnectionPoolException} with the specified cause and a
     * detail message of {@code (cause==null ? null : cause.toString())}
     * (which typically contains the class and detail message of {@code cause}).
     *
     * @param cause The cause (which is saved for later retrieval by the
     *              {@link #getCause()} method).  (A null value is permitted,
     *              and indicates that the cause is nonexistent or unknown.)
     */
    public ConnectionPoolException(Throwable cause) {
        super(cause);
    }

}
