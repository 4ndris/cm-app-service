package hu.vereba.snippet.cm.exception;

public class InvalidCategoryException extends RuntimeException{

    public InvalidCategoryException() {
        super();
    }

    public InvalidCategoryException(String message) {
        super(message);
    }

    public InvalidCategoryException(Throwable cause) {
        super(cause);
    }

    public InvalidCategoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
