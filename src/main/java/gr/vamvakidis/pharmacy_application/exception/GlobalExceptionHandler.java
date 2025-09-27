package gr.vamvakidis.pharmacy_application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles all uncaught exceptions in the application.
     * Returns an HTTP 500 (Internal Server Error) response with a message.
     *
     * @param ex The exception that was thrown
     * @return ResponseEntity with status 500 and the exception message
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + ex.getMessage());
    }

    /**
     * Handles ResourceNotFoundException specifically.
     * Returns an HTTP 404 (Not Found) response with the exception message.
     *
     * @param ex The ResourceNotFoundException that was thrown
     * @return ResponseEntity with status 404 and the exception message
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFound(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
