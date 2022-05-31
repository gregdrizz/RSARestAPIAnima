package homeassignment.animabiotech.rsarestapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.security.NoSuchAlgorithmException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> wrongInput(IllegalArgumentException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchAlgorithmException.class)
    public ResponseEntity<Object> couldNotCreateKeyInternal(IllegalArgumentException ex, WebRequest request) {
        return new ResponseEntity<>("An error occurred while creating rsa-key, please contact support",
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
