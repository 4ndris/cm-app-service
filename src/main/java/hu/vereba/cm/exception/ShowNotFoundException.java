package hu.vereba.cm.exception;

public class ShowNotFoundException extends RuntimeException {

    private static final String msg_prefix = "Not found ";

    public ShowNotFoundException() {
        super();
    }

    public ShowNotFoundException(String message, Throwable cause) {
        super(msg_prefix +message, cause);
    }

    public ShowNotFoundException(String message) {
        super(msg_prefix + message);
    }

    public ShowNotFoundException(Throwable cause) {
        super(cause);
    }
}
