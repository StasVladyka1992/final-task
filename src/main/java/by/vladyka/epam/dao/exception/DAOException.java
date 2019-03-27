package by.vladyka.epam.dao.exception;

/**
 * DAOException signals that error occurred during interacting with database or
 * other data storage
 *
 * @author Stas Vladyka
 * @version 1.0
 * @see Exception
 **/
public class DAOException extends Exception {

    /**
     * Constructs an {@code DAOException} with {@code null}
     * as its error detail message.
     */
    public DAOException() {
    }

    /**
     * Constructs an {@code DAOException} with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval
     *                by the {@link #getMessage()} method)
     */
    public DAOException(String message) {
        super(message);
    }

    /**
     * Constructs an {@code DAOException} with the specified detail message
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
    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs an {@code DAOException} with the specified cause and a
     * detail message of {@code (cause==null ? null : cause.toString())}
     * (which typically contains the class and detail message of {@code cause}).
     *
     * @param cause The cause (which is saved for later retrieval by the
     *              {@link #getCause()} method).  (A null value is permitted,
     *              and indicates that the cause is nonexistent or unknown.)
     */
    public DAOException(Throwable cause) {
        super(cause);
    }
}
