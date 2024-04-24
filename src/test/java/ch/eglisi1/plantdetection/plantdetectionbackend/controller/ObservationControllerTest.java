package ch.eglisi1.plantdetection.plantdetectionbackend.controller;

import ch.eglisi1.plantdetection.plantdetectionbackend.model.PredictionDbo;
import ch.eglisi1.plantdetection.plantdetectionbackend.repository.PredictionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

class ObservationControllerTest {

    @Mock
    private PredictionRepository predictionRepository;

    @InjectMocks
    private ObservationController observationController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getObservations_ReturnsListOfPredictions() {
        // Arrange
        PredictionDbo prediction = new PredictionDbo();
        Mockito.when(predictionRepository.findAll()).thenReturn(List.of(prediction));

        // Act
        ResponseEntity<List<PredictionDbo>> response = observationController.getObservations();

        // Assert
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(1, response.getBody().size());
        Mockito.verify(predictionRepository).findAll();
    }

    @Test
    void getObservations_ThrowsException_ReturnsInternalServerError() {
        // Arrange
        Mockito.when(predictionRepository.findAll()).thenThrow(new RuntimeException("Database error"));

        // Act
        ResponseEntity<List<PredictionDbo>> response = observationController.getObservations();

        // Assert
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        Assertions.assertNull(response.getBody());
        Mockito.verify(predictionRepository).findAll();
    }
}