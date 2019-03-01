package by.vladyka.epam.dao.exception;

/**
 * Created by Vladyka Stas
 * on 19.02.2019 at 19:34
 **/
public class NoDBDriverException extends Exception {
    public NoDBDriverException() {
    }

    public NoDBDriverException(String message) {
        super(message);
    }

    public NoDBDriverException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoDBDriverException(Throwable cause) {
        super(cause);
    }

    public NoDBDriverException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
