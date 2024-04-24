package ch.eglisi1.plantdetection.plantdetectionbackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;

/**
 * Global exception handler for the entire application.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Exception handler for RestClientResponseException.
     * @param exception The exception to handle.
     * @return A response entity with the status code and the response body.
     */
    @ExceptionHandler(RestClientResponseException.class)
    public ResponseEntity<String> handleRestClientException(RestClientResponseException exception) {
        return new ResponseEntity<>(exception.getResponseBodyAsString(), exception.getStatusCode());
    }

    /**
     * Exception handler for ResourceAccessException.
     * @param exception The exception to handle.
     * @return A response entity with the status code and the response body.
     */
    @ExceptionHandler(ResourceAccessException.class)
    public ResponseEntity<String> handleResourceAccessExceptions(ResourceAccessException exception) {
        return new ResponseEntity<>("Model API not available", HttpStatus.NOT_FOUND);
    }
}
