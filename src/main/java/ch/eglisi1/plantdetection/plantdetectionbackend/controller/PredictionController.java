package ch.eglisi1.plantdetection.plantdetectionbackend.controller;

import ch.eglisi1.plantdetection.plantdetectionbackend.schema.PredictionResponseModel;
import ch.eglisi1.plantdetection.plantdetectionbackend.service.PredictionService;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PredictionController {
    private final Logger logger = org.slf4j.LoggerFactory.getLogger(PredictionController.class);
    private final PredictionService predictionService;

    public PredictionController(PredictionService predictionService) {
        this.predictionService = predictionService;
    }

    @PostMapping("/predict")
    public PredictionResponseModel predict(@RequestBody String base64Image) {
        logger.debug("Received base64Image: " + base64Image);
        return predictionService.predict(base64Image);
    }
}
