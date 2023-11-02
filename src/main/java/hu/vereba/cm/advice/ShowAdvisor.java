package hu.vereba.cm.advice;

import hu.vereba.cm.exception.DuplicateShowIdException;
import hu.vereba.cm.exception.ShowNotFoundException;
import hu.vereba.cm.rest.model.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ShowAdvisor {

    @ExceptionHandler(ShowNotFoundException.class)
    ResponseEntity<?> showNotFoundHandler(ShowNotFoundException ex) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateShowIdException.class)
    ResponseEntity<?> duplicateShowIdHandler(DuplicateShowIdException ex) {
        ErrorMessage error = new ErrorMessage();
        error.setError("Unique ID check Failed");
        error.setDetails(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        ErrorMessage errorDetails = new ErrorMessage();
        errorDetails.setError("Validation Failed");
        errorDetails.setDetails(ex.getBindingResult().toString());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
