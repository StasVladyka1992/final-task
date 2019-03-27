package by.vladyka.epam.service.exception;

/**
 * ServiceException signals that error occurred in application business logic
 *
 * @author Stas Vladyka
 * @version 1.0
 * @see Exception
 */
public class ServiceException extends Exception {

    /**
     * Constructs an {@code ServiceException} with {@code null}
     * as its error detail message.
     */
    public ServiceException() {
    }

    /**
     * Constructs an {@code ServiceException} with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval
     *                by the {@link #getMessage()} method)
     */
    public ServiceException(String message) {
        super(message);
    }

    /**
     * Constructs an {@code ServiceException} with the specified detail message
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
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs an {@code ServiceException} with the specified cause and a
     * detail message of {@code (cause==null ? null : cause.toString())}
     * (which typically contains the class and detail message of {@code cause}).
     *
     * @param cause The cause (which is saved for later retrieval by the
     *              {@link #getCause()} method).  (A null value is permitted,
     *              and indicates that the cause is nonexistent or unknown.)
     */
    public ServiceException(Throwable cause) {
        super(cause);
    }

}
