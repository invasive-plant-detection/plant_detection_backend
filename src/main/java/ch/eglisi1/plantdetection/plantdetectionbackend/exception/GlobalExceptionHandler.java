package ch.eglisi1.plantdetection.plantdetectionbackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RestClientResponseException.class)
    public ResponseEntity<String> handleRestClientException(RestClientResponseException exception) {
        return new ResponseEntity<>(exception.getResponseBodyAsString(), exception.getStatusCode());
    }

    @ExceptionHandler(ResourceAccessException.class)
    public ResponseEntity<String> handleResourceAccessExceptions(ResourceAccessException exception) {
        return new ResponseEntity<>("Model API not available", HttpStatus.NOT_FOUND);
    }
}
