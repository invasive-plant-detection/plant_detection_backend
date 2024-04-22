package ch.eglisi1.plantdetection.plantdetectionbackend.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.ResourceAccessException;

@SpringBootTest
@ActiveProfiles("test")
class PredictionServiceTest {
    @Autowired
    PredictionService predictionService;

    @Test
    void predict_serviceOffline() {
        // act
        Exception exception = Assertions.assertThrows(ResourceAccessException.class, () -> predictionService.predict("foo"));

        // assert
        Assertions.assertFalse(exception.getMessage().isEmpty());
    }
}