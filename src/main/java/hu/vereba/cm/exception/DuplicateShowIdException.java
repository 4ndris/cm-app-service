package hu.vereba.cm.exception;

public class DuplicateShowIdException extends RuntimeException {

    private static final String msg_prefix = "Duplicate ";

    public DuplicateShowIdException() {
        super();
    }

    public DuplicateShowIdException(String message, Throwable cause) {
        super(msg_prefix +message, cause);
    }

    public DuplicateShowIdException(String message) {
        super(msg_prefix + message);
    }

    public DuplicateShowIdException(Throwable cause) {
        super(cause);
    }
}
