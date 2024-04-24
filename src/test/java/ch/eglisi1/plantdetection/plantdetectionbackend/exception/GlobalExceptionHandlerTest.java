package ch.eglisi1.plantdetection.plantdetectionbackend.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;

class GlobalExceptionHandlerTest {
    private final GlobalExceptionHandler exceptionHandler = new GlobalExceptionHandler();

    @Test
    void handleRestClientException_ReturnsResponseBodyAndStatus() {
        // Given
        RestClientResponseException exception = new RestClientResponseException(
                "Error occurred", 404, "Not Found", null, "Error response body".getBytes(), null);

        // When
        ResponseEntity<String> response = exceptionHandler.handleRestClientException(exception);

        // Then
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Assertions.assertEquals("Error response body", response.getBody());
    }

    @Test
    void handleResourceAccessExceptions_ReturnsNotFound() {
        // Given
        ResourceAccessException exception = new ResourceAccessException("Model API not available");

        // When
        ResponseEntity<String> response = exceptionHandler.handleResourceAccessExceptions(exception);

        // Then
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Assertions.assertEquals("Model API not available", response.getBody());
    }
}