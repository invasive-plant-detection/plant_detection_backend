package ch.eglisi1.plantdetection.plantdetectionbackend.controller;

import ch.eglisi1.plantdetection.plantdetectionbackend.schema.IncomingPredictionRequestModel;
import ch.eglisi1.plantdetection.plantdetectionbackend.schema.PredictionResponseModel;
import ch.eglisi1.plantdetection.plantdetectionbackend.service.PredictionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;

class PredictionControllerTest {

    @Mock
    private PredictionService predictionService;

    @InjectMocks
    private PredictionController predictionController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void predict_ReturnsCorrectResponseModel() {
        // Arrange
        IncomingPredictionRequestModel request = new IncomingPredictionRequestModel("image_base64", BigDecimal.ONE, BigDecimal.TWO);
        PredictionResponseModel expectedResponse = new PredictionResponseModel("Plant type detected", "Instruction");
        Mockito.when(predictionService.predict(ArgumentMatchers.anyString(), ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(expectedResponse);

        // Act
        PredictionResponseModel actualResponse = predictionController.predict(request);

        // Assert
        Assertions.assertNotNull(actualResponse);
        Assertions.assertEquals(expectedResponse, actualResponse);
        Mockito.verify(predictionService).predict(request.base64Image(), request.latitude(), request.longitude());
    }

    @Test
    void predict_ThrowsException_ReturnsErrorResponse() {
        // Arrange
        IncomingPredictionRequestModel request = new IncomingPredictionRequestModel("invalid_image", BigDecimal.ZERO, BigDecimal.TEN);
        when(predictionService.predict(ArgumentMatchers.anyString(), ArgumentMatchers.any(), ArgumentMatchers.any())).thenThrow(new RuntimeException("Failed to process image"));

        // Act & Assert
        Exception exception = Assertions.assertThrows(RuntimeException.class, () -> predictionController.predict(request));
        Assertions.assertEquals("Failed to process image", exception.getMessage());
        Mockito.verify(predictionService).predict(request.base64Image(), request.latitude(), request.longitude());
    }
}