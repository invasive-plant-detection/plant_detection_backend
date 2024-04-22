package ch.eglisi1.plantdetection.plantdetectionbackend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class PlantDetectionBackendApplicationTests {

    @Test
    void contextLoads() {
        System.out.println("Context loaded");
    }

}
