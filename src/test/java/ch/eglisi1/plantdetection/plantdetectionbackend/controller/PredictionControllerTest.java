package ch.eglisi1.plantdetection.plantdetectionbackend.controller;

import ch.eglisi1.plantdetection.plantdetectionbackend.schema.PredictionResponseModel;
import ch.eglisi1.plantdetection.plantdetectionbackend.service.PredictionService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class PredictionControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PredictionService predictionService;

    @InjectMocks
    private PredictionController predictionController;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(predictionController).build();
    }

    @Test
    void predict() throws Exception {
        String testImage = "testImageString";
        String mockInstruction = "This is a dummy instruction";
        BigDecimal longitude = BigDecimal.ONE;
        BigDecimal latitude = BigDecimal.ONE;

        PredictionResponseModel mockResponse = new PredictionResponseModel(testImage, mockInstruction);

        BDDMockito.given(predictionService.predict(ArgumentMatchers.eq(testImage), latitude, longitude))
                .willReturn(mockResponse);

        mockMvc.perform(MockMvcRequestBuilders.post("/predict")
                        .content(testImage)
                        .contentType(MediaType.TEXT_PLAIN))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString(mockInstruction)))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString(testImage)))
        ;
    }
}