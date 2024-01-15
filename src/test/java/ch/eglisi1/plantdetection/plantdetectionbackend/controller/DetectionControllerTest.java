package ch.eglisi1.plantdetection.plantdetectionbackend.controller;

import ch.eglisi1.plantdetection.plantdetectionbackend.schema.PredictionResponseModel;
import ch.eglisi1.plantdetection.plantdetectionbackend.service.DetectionService;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
class DetectionControllerTest {

    private MockMvc mockMvc;

    @Mock
    private DetectionService detectionService;

    @InjectMocks
    private DetectionController detectionController;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(detectionController).build();
    }

    @Test
    void predict() throws Exception {
        String testImage = "testImageString";
        String mockInstruction = "This is a dummy instruction";

        PredictionResponseModel mockResponse = new PredictionResponseModel(testImage, mockInstruction);

        BDDMockito.given(detectionService.predict(ArgumentMatchers.eq(testImage)))
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