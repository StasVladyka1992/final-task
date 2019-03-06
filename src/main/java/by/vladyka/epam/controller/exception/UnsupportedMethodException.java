package by.vladyka.epam.controller.exception;

/**
 * Created by Vladyka Stas
 * on 06.03.2019 at 2:39
 **/
public class UnsupportedMethodException extends Exception {
    public UnsupportedMethodException() {
    }

    public UnsupportedMethodException(String message) {
        super(message);
    }

    public UnsupportedMethodException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnsupportedMethodException(Throwable cause) {
        super(cause);
    }

    public UnsupportedMethodException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
