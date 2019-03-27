package by.vladyka.epam.dao.exception;

/**
 * InitializationPoolException signals that error occurred during initialization
 * pool connection to database. Unchecked exception
 *
 * @author Stas Vladyka
 * @version 1.0
 * @see Exception
 **/
public class InitializationPoolException extends RuntimeException {

    /**
     * Constructs an {@code InitializationPoolException} with {@code null}
     * as its error detail message.
     */
    public InitializationPoolException() {
    }

    /**
     * Constructs an {@code InitializationPoolException} with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval
     *                by the {@link #getMessage()} method)
     */
    public InitializationPoolException(String message) {
        super(message);
    }

    /**
     * Constructs an {@code InitializationPoolException} with the specified detail message
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
    public InitializationPoolException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs an {@code InitializationPoolException} with the specified cause and a
     * detail message of {@code (cause==null ? null : cause.toString())}
     * (which typically contains the class and detail message of {@code cause}).
     *
     * @param cause The cause (which is saved for later retrieval by the
     *              {@link #getCause()} method).  (A null value is permitted,
     *              and indicates that the cause is nonexistent or unknown.)
     */
    public InitializationPoolException(Throwable cause) {
        super(cause);
    }
}
