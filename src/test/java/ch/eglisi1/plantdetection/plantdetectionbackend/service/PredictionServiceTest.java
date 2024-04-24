package ch.eglisi1.plantdetection.plantdetectionbackend.service;

import ch.eglisi1.plantdetection.plantdetectionbackend.model.PredictionDbo;
import ch.eglisi1.plantdetection.plantdetectionbackend.repository.PredictionRepository;
import ch.eglisi1.plantdetection.plantdetectionbackend.schema.PredictionResponseModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class PredictionServiceTest {

    @Mock
    private PredictionRepository predictionRepository;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private PredictionService predictionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void predict_SuccessfulPrediction_SavesAndReturnsData() {
        // Arrange
        String image = "base64Image";
        String instruction = "Instruction";
        BigDecimal latitude = new BigDecimal("10.0");
        BigDecimal longitude = new BigDecimal("20.0");
        PredictionResponseModel responseModel = new PredictionResponseModel("Plant is healthy", instruction);
        Mockito.when(restTemplate.postForEntity(ArgumentMatchers.anyString(), ArgumentMatchers.any(), ArgumentMatchers.eq(PredictionResponseModel.class)))
                .thenReturn(new ResponseEntity<>(responseModel, HttpStatus.OK));

        // Act
        PredictionResponseModel result = predictionService.predict(image, latitude, longitude);

        // Assert
        assertNotNull(result);
        assertEquals(instruction, result.instruction());
        Mockito.verify(predictionRepository).save(ArgumentMatchers.any(PredictionDbo.class));
    }

    @Test
    void predict_ServiceThrowsException_DoesNotSaveData() {
        // Arrange
        Mockito.when(restTemplate.postForEntity(ArgumentMatchers.anyString(), ArgumentMatchers.any(), ArgumentMatchers.eq(PredictionResponseModel.class)))
                .thenThrow(new RestClientResponseException("Error", 404, "Not Found", null, null, null));

        // Act & Assert
        assertThrows(RestClientResponseException.class, () ->
                predictionService.predict("base64Image", new BigDecimal("10.0"), new BigDecimal("20.0")));

        Mockito.verify(predictionRepository, Mockito.never()).save(ArgumentMatchers.any(PredictionDbo.class));
    }
}