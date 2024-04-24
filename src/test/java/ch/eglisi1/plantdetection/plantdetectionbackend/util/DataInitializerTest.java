package ch.eglisi1.plantdetection.plantdetectionbackend.util;

import ch.eglisi1.plantdetection.plantdetectionbackend.model.PredictionDbo;
import ch.eglisi1.plantdetection.plantdetectionbackend.repository.PredictionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.test.util.ReflectionTestUtils;

class DataInitializerTest {


    @Mock
    private PredictionRepository predictionRepository;

    @InjectMocks
    private DataInitializer dataInitializer;

    @BeforeEach
    void setUp() throws NullPointerException {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(dataInitializer, "environmentName", "DEV");
    }

    @Test
    void initData_CreatesPredictionsInDevEnvironment() {
        // Arrange
        Mockito.when(predictionRepository.save(ArgumentMatchers.any(PredictionDbo.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        dataInitializer.initData();

        // Assert
        Mockito.verify(predictionRepository, Mockito.times(DataInitializer.ENTITIES_TO_CREATE)).save(ArgumentMatchers.any(PredictionDbo.class));
    }
}
