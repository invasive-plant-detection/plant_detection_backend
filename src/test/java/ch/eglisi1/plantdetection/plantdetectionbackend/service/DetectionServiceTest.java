package ch.eglisi1.plantdetection.plantdetectionbackend.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.ResourceAccessException;

@SpringBootTest
//@TestPropertySource("classpath:application.properties")
class DetectionServiceTest {
    @Autowired
    DetectionService detectionService;

    @Test
    void predict_serviceOffline() {
        // act
        Exception exception = Assertions.assertThrows(ResourceAccessException.class, () -> detectionService.predict("foo"));

        // assert
        Assertions.assertFalse(exception.getMessage().isEmpty());
    }
}