package by.vladyka.epam.dao.exception;

/**
 * Created by Vladyka Stas
 * on 07.03.2019 at 0:52
 **/
public class InitializationPoolException extends RuntimeException {
    public InitializationPoolException() {
    }

    public InitializationPoolException(String message) {
        super(message);
    }

    public InitializationPoolException(String message, Throwable cause) {
        super(message, cause);
    }

    public InitializationPoolException(Throwable cause) {
        super(cause);
    }

    public InitializationPoolException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
